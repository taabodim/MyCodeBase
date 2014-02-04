package com.admarketplace.isg.myEventBus;

import java.lang.reflect.Method;

public class EventWrapper {
	public Object event;
	public Class<? extends Object> subscribedForEventClass;
	public Method subscribedForEventMethod;
	public String eventType;

	public EventWrapper(Class<? extends Object> eventClass, Method method, Object event, String eventType) {
		subscribedForEventMethod = method;
		subscribedForEventClass = eventClass;
		this.eventType = eventType;
		this.event = event;
	}

}
