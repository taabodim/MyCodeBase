package com.admarketplace.isg.jms;

import com.admarketplace.isg.util.Util;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.concurrent.atomic.AtomicLong;

/*receive a message from queue on local tibco*/
public class MessageReceiver implements MessageListener, ExceptionListener {

	
	private static AtomicLong numberOfMsgReceived = new AtomicLong(0);
	private Session session =null;
	
	public MessageReceiver() throws JMSException {
		String serverUrl = "tcp://localhost:7222";
		String queueName = "com.admarketplace.isg.queue.mahmoud";

		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				serverUrl);

		int numberOfConnections=200;
		for (int i = 0; i < numberOfConnections; i++) {
			startAConnection(connectionFactory, queueName);

		}

	}

	private void startAConnection(ConnectionFactory connectionFactory,String queueName) throws JMSException {
		
		Connection c = connectionFactory.createConnection("ddi", "ddipass");
		
		

		  session = c.createSession(true, Session.SESSION_TRANSACTED);
		
		Destination destination = session.createQueue(queueName);

		c.setExceptionListener(this);

		MessageConsumer consumer = session.createConsumer(destination);

		consumer.setMessageListener(this);

		c.start();

	}

	@Override
	public void onMessage(Message message) {
		try {
			numberOfMsgReceived.incrementAndGet();
			Util.echo(numberOfMsgReceived + "th message received "
					+ message.toString());
			message.acknowledge();
			session.commit();
		} catch (Exception e) {
			// Message processing failed.
			// Do whatever you need to do here for the exception.

			// NOTE: You may need to check the redelivery count of this message
			// first
			// and just commit it after it fails a predefined number of times
			// (Make sure you
			// store it somewhere if you don't want to lose it). This way you're
			// process isn't
			// handling the same failed message over and over again.

		}
	}

	@Override
	public void onException(JMSException arg0) {

		try {
			session.rollback();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void  main(String args[]) throws InterruptedException, JMSException
	{
		
		MessageReceiver msgReceiver = new MessageReceiver();
		Util.echo("msgReceiver has started..... ");
	
		
	}
}