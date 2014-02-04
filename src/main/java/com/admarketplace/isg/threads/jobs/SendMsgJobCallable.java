package com.admarketplace.isg.threads.jobs;

import com.admarketplace.isg.jms.MessageSender;

import javax.jms.JMSException;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicLong;

public class SendMsgJobCallable<V> implements Callable<V> {

	public static AtomicLong jobsProcessed = new AtomicLong(0);
	private static MessageSender msgSender;

	

	private static AtomicLong msgNumber = new AtomicLong(0);

	public SendMsgJobCallable() throws JMSException {
		if (msgSender == null)
			msgSender = new MessageSender();
		
	}

	@Override
	public V call() throws Exception {

		msgNumber.incrementAndGet();
		try {

			System.out.println("sending msg....msgNumber : " + msgNumber);

				msgSender.sendTextToQueue();
				jobsProcessed.incrementAndGet();

			} catch (Exception e) {

				e.printStackTrace();
			}
	

		return (V) jobsProcessed;
	}
}
