package com.admarketplace.isg.jmx;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.management.MBeanAttributeInfo;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.FileInputStream;
import java.util.*;



public class NSLostMessageProcessor {
	private JMXServiceURL url;
	private final static  Logger logger = LogManager.getLogger(NSLostMessageProcessor.class.getName());
	private String[] appNames;
	private String username;
	private String password;
	private static MBeanServerConnection remoteServer;
	private static NSLostMessageProcessor INSTANCE;

	private NSLostMessageProcessor() {
	}

	public NSLostMessageProcessor getInstance() {
		return INSTANCE;
	}

	public static void main(String args[]) throws Exception {

		findTheLog4JFile();
		NSLostMessageProcessor obj = new NSLostMessageProcessor();
		obj.setValues(obj.loadProperties("jmx-config.properties"));
		obj.connectToRemoteServer();
		obj.processLostMessagesToDueCrash();

	}

	private static void findTheLog4JFile() {
//		FileAppender fileAppender = null;
//		 Object appenders = logger.getRootLogger().getAllAppenders();
//		while(appenders.hasMoreElements()) {
//
//		    Appender currAppender = (Appender) appenders.nextElement();
//		    if(currAppender instanceof FileAppender) {
//		        fileAppender = (FileAppender) currAppender;
//		    }
//		}
//
//		if(fileAppender != null) {
//		    logDest = fileAppender.getFile();
//		    System.out.println("logDest : " + logDest);
//		}
	}

	private void connectToRemoteServer() throws Exception {
		Map<String, String[]> environment = new HashMap<String, String[]>();
		environment.put(JMXConnector.CREDENTIALS, new String[] { username, password });

		JMXConnector jmxConnector = JMXConnectorFactory.newJMXConnector(url, environment);
		jmxConnector.connect(environment);

		remoteServer = jmxConnector.getMBeanServerConnection();

	}

	private void setValues(Properties props) throws Exception {
		String env = props.getProperty("jmx.env");
		String urlKey = "jmx.url." + env;
		url = new JMXServiceURL(props.getProperty(urlKey));
		appNames = props.getProperty("app.names").split(";");
		username = props.getProperty("jmx.user");
		password = props.getProperty("jmx.password");
	}

	private Properties loadProperties(String configFile) throws Exception {
		Properties props = new Properties();
		props.load(new FileInputStream(configFile));
		return props;
	}

	public void processLostMessagesToDueCrash() throws Exception {
		String operationName = "processLostMessagesOlderThan5MinutesDueToCrash";
		ArrayList<String> allMbeansToBeInvoked = new ArrayList<String>();

		for (int i = 0; i < appNames.length; i++) {

			allMbeansToBeInvoked.addAll(matchMBeans(appNames[i]));
			logger.info(" allMbeansToBeInvoked are  " + allMbeansToBeInvoked.toString());
		}
		logger.info(" allMbeansToBeInvoked size is " + allMbeansToBeInvoked.size());
		for (int j = 0; j < allMbeansToBeInvoked.size(); j++) {
			logger.info(this.getClass().getCanonicalName() + " going to " + operationName + " operation on this mbean"
					+ allMbeansToBeInvoked.size());
			ObjectName name = new ObjectName(allMbeansToBeInvoked.get(j));
			invokeAnMbeanOperation(name, operationName, null, null, username, password);

		}

	}

	public String invokeAnMbeanOperation(ObjectName name, String operationName, Object opParams[], String opSig[],
			String jmxUsername, String jmxPassword) {

		try {
			// Invoke operation
			String res = (String) remoteServer.invoke(name, operationName, opParams, opSig);
			logger.info("response from invoking operation is " + res);
			return res;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	private ArrayList<String> matchMBeans(String wildCardName) throws Exception {
		ArrayList<String> allMbeans = getAllTheMBeansNameMBeanServerMatchingWildCard(remoteServer, wildCardName);

		return allMbeans;
	}

	private ArrayList<String> getAllTheMBeansNameMBeanServerMatchingWildCard(MBeanServerConnection server,
			String wildCardName) throws Exception {
		String objectType = "NSProfiler";
		ArrayList<String> allMbeanNames = new ArrayList<String>();

		Set<ObjectName> mbeans = new HashSet<ObjectName>();
		ObjectName name = new ObjectName("" + wildCardName + ":type=" + objectType);
		mbeans = server.queryNames(name, null);
		for (ObjectName mbean : mbeans) {
			logger.info("mbean name is " + mbean.getCanonicalName());
			allMbeanNames.add(mbean.getCanonicalName());
			MBeanAttributeInfo[] attributes = server.getMBeanInfo(mbean).getAttributes();
			for (MBeanAttributeInfo attribute : attributes) {

				try {
					final Object value = server.getAttribute(mbean, attribute.getName());
					if (value == null) {
					} else {
						// logger.info(value.toString());
					}
				} catch (Exception e) {
				}

			}
		}

		return allMbeanNames;
	}

}
