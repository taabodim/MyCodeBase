package com.admarketplace.isg.myEventBus;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.concurrent.ArrayBlockingQueue;

public class MyEventBus {
	
	public ArrayBlockingQueue<EventWrapper> events = new ArrayBlockingQueue<EventWrapper>(1000);
	ArrayBlockingQueue<Object> subscriberClasses = new ArrayBlockingQueue<Object>(100);

	public void post(Event event) throws Exception {

		while (!subscriberClasses.isEmpty()) {

			Object subscribedClass = subscriberClasses.poll();

			Class<? extends Object> subscribedForEventClass = subscribedClass.getClass();
			String nameOfClass = subscribedForEventClass.getSimpleName();
			Method[] methods = subscribedForEventClass.getDeclaredMethods();
			for (int i = 0; i < methods.length; i++) {

				MyEventSubscriber annotation = methods[i].getAnnotation(MyEventSubscriber.class);
				String name = methods[i].getName();
				if (annotation != null) {
					EventWrapper eventWrapper = new EventWrapper(subscribedForEventClass, methods[i], event, event
							.getClass().getCanonicalName());
					events.add(eventWrapper);
				}
			}
			if (events.isEmpty() == false)
				dispatchEvents();
		}

	}

	public void register(Object subscriberClass) {
		subscriberClasses.add(subscriberClass);
	}

	private void dispatchEvents() throws Exception {
		while (!events.isEmpty()) {
			EventWrapper event = events.poll();

			Object obj = event.subscribedForEventClass.newInstance();

			Type[] parameterTypes = event.subscribedForEventMethod.getGenericParameterTypes();
			if (parameterTypes.length == 0)
				event.subscribedForEventMethod.invoke(obj);
			else {
				String paramType = parameterTypes[0].toString().substring(6,parameterTypes[0].toString().length());
				String eventType = event.eventType;
				// Object eventObj = event.event;
				if (parameterTypes.length == 1 && paramType.equalsIgnoreCase(eventType))
					event.subscribedForEventMethod.invoke(obj, event.event);
				
				event=null;
				obj=null;
			}
		}
	}

}
