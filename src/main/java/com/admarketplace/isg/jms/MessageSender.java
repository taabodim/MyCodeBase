package com.admarketplace.isg.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MessageSender {
	/* send a message to queue or topic on local tibco */
	private static String serverUrl = "tcp://localhost:7222";
	private static String queueName = "com.admarketplace.isg.queue.mahmoud";
	private static ConnectionFactory connectionFactory = null;
	private static Connection connection = null;
	private static Session session = null;
	private static Destination queue = null;
	private static MessageProducer queueSender = null;
	private static String msgAsString;

	public MessageSender() throws JMSException {
		if (connectionFactory == null)
			connectionFactory = new ActiveMQConnectionFactory(serverUrl);

		if (connection == null)
			connection = connectionFactory.createConnection("ddi", "ddipass");

		if (session == null)
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		if (queue == null)
			queue = session.createQueue(queueName);

		if (queueSender == null)
			queueSender = session.createProducer(queue);

	}

	public void sendTextToQueue() throws Exception {

		if(msgAsString==null)
			 msgAsString = readMsgContentFromFile("C:\\w\\MyProject\\src\\main\\resource\\normal_notification14kb_equity.txt");
		
		TextMessage message = session.createTextMessage(msgAsString);
		message.setJMSDeliveryMode(DeliveryMode.NON_PERSISTENT);
		queueSender.send(message);
		message=null;
		

	}

	private String readMsgContentFromFile(String filename) throws IOException {

		File file = new File(filename);
		FileReader fileReader = new FileReader(file);
		BufferedReader reader = new BufferedReader(fileReader);
		String line = null;
		StringBuilder stringBuilder = new StringBuilder();
		String ls = System.getProperty("line.separator");

		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line);
			stringBuilder.append(ls);
		}
		reader.close();
		reader=null;
		file=null;
		fileReader = null;
		
		return stringBuilder.toString();
	}

	public void sendTextToTopic() throws Exception {

		String serverUrl = "tcp://localhost:7222";

		String topicName = "com.admarketplace.isg.topic.mahmoud";

		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(serverUrl);

		Connection connection = connectionFactory.createConnection("ddi", "ddipass");
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		Destination topic = session.createTopic(topicName);
		MessageProducer topicSender = session.createProducer(topic);

		TextMessage message = session.createTextMessage("hello taabodi");
		topicSender.send(message);

		connection.close();

	}
}
