package com.admarketplace.disruptor.example;

import java.util.concurrent.atomic.AtomicLong;

import com.lmax.disruptor.RingBuffer;

public class MyDisruptorPublisher implements Runnable {

	private RingBuffer<MyDisruptorEvent> ringBuffer;
	// private static AtomicLong numEventsToPublish = new
	// AtomicLong(DisruptorPattern.numEventsToPublish);
	private AtomicLong counter = new AtomicLong(0);

	public MyDisruptorPublisher(RingBuffer<MyDisruptorEvent> myRingBuffer) {
		ringBuffer = myRingBuffer;
	}

	@Override
	public void run() {

		while (counter.get() <= DisruptorPattern.numEventsToPublish) {
			counter.incrementAndGet();
			publishToRingBuffer();
		}
	}

	private void publishToRingBuffer() {

		long sequence = ringBuffer.next();
		// System.out.println("sequence from ringBuffer.next() is "+sequence);
		MyDisruptorEvent event = ringBuffer.get(sequence);
		// System.out.println("event is "+event.toString());
//		String str = event+" is modifed by publisher,sequence for this change was "+sequence;
//		event.setJsonDocument(str);
		event.setEventId(sequence);
		ringBuffer.publish(sequence);
//		 System.out.println("sequenced is published sequence "+ sequence);
	}

}
