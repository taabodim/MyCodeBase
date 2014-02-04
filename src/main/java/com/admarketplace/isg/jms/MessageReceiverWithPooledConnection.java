package com.admarketplace.isg.jms;

import com.admarketplace.isg.util.Util;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.concurrent.atomic.AtomicLong;

/*receive a message from queue on local tibco*/
public class MessageReceiverWithPooledConnection implements MessageListener,
		ExceptionListener {

	private static AtomicLong numberOfMsgReceived = new AtomicLong(0);
	 private Session session =null;
	private String serverUrl = "tcp://localhost:7222";
	private String queueName = "com.admarketplace.isg.queue.mahmoud";
	private ConnectionFactory connectionFactory;

	public MessageReceiverWithPooledConnection() throws JMSException {

		if (connectionFactory == null)
			startAConnection(serverUrl, queueName);

//		int numberOfConnections = 200;
//		for (int i = 0; i < numberOfConnections; i++) {
//			startAConnection(serverUrl, queueName);
//
//		}

	}

	private void startAConnection(String serverUrl, String queueName)
			throws JMSException {

		 connectionFactory = new ActiveMQConnectionFactory(
				serverUrl);

		Connection c = connectionFactory.createConnection("ddi", "ddipass");

		Session session = c.createSession(true, Session.SESSION_TRANSACTED);

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

	public static void main(String args[]) throws InterruptedException,
			JMSException {

		MessageReceiverWithPooledConnection msgReceiver = new MessageReceiverWithPooledConnection();
		Util.echo("msgReceiver has started..... ");

	}
}