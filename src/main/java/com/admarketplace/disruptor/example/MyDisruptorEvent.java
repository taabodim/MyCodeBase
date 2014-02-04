package com.admarketplace.disruptor.example;

import java.util.concurrent.atomic.AtomicInteger;

import com.lmax.disruptor.EventFactory;


public class MyDisruptorEvent {
	private static AtomicInteger counterEvents = new AtomicInteger(-1);
	private long eventId;

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}
	private String jsonDocument;

	public MyDisruptorEvent(String value,int id) {
		jsonDocument = value;
		eventId = id;
	}

	public String getJsonDocument() {
		return jsonDocument;
	}

	public void setJsonDocument(String jsonDocument) {
		this.jsonDocument = jsonDocument;
	}
	  public static final EventFactory<MyDisruptorEvent> EVENT_FACTORY = new EventFactory<MyDisruptorEvent>()
			    {
			        public MyDisruptorEvent newInstance()
			        {
//			        	System.out.println("creating a new event to put in RingBuffer..");
			        	counterEvents.incrementAndGet();
			              return new MyDisruptorEvent("{\"id\":\"this is a json document\"}", counterEvents.get());
			        }
			    };
}
