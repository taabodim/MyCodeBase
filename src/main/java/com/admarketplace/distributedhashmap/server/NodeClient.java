package com.admarketplace.distributedhashmap.server;

import com.admarketplace.isg.util.Util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NodeClient implements Node {
	public int clientPort = 8999;
	private transient ServerSocket clientSocket;
	public transient static ConcurrentHashMap<String, String> clientVersionOfServerData = new ConcurrentHashMap<String, String>();
	public transient static ConcurrentHashMap<String, String> clientCreatedData = new ConcurrentHashMap<String, String>();
	private transient ExecutorService executorService = Executors
			.newFixedThreadPool(5);

	private String nodeId;
	private byte[] clientAddress;

	public NodeClient(int i) throws Exception {
		clientPort = i;
		this.nodeId = "node" + i;
		clientSocket = new ServerSocket(clientPort);
		clientAddress = clientSocket.getInetAddress().getAddress();
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public static void main(String[] args) {

		for (int i = 0; i < 1; i++) {
			long portNum = (long) Util.random(1000);
			NodeClient myClient;
			try {
				myClient = new NodeClient(1900 + (int) portNum);
				myClient.startLifeCycle();
			} catch (Exception e) {

				e.printStackTrace();
			}

		}
	}

	public void startLifeCycle() throws InterruptedException, IOException {
		startListening();
		tellServerWhatPortIamListeningto();
		while (true) {
			clientUpdatesTheMap();

			tellServerMapIsUpdated();
			Thread.sleep(20000);
		}
	}

	private synchronized void tellServerWhatPortIamListeningto()
			throws IOException {
		Data data = new Data(this, "IamListeningToThisPort",
				new ConcurrentHashMap<String, String>(), clientPort);
		// Util.echo("going to tell server ....");

		TellSomeBodySomething word = new TellSomeBodySomething(
				InetAddress.getByName("localhost"), NodeServer.serverPort, data);
		word.connectSpeakDisconnect();
	}

	private void startListening() {

		executorService.submit(new ClientNodeListeningService(clientSocket));
	}

	public void clientUpdatesTheMap() {
		String nameArray[] = new String[] { "ali", "reza", "mehdi", "mahmoud",
				"leo", "peter", "john", "martin", "janet", "emily", "maryam",
				"sara", "amin", "sina", "ashkan", "hossein" };
		int arrayIndex = (int) Util.random(nameArray.length);
		// Util.echo("arrayIndex is " + arrayIndex);

		long limit = (long) Util.random(100);
		limit = 1;
		for (int i = 0; i < limit; i++) {
			long id = (long) Util.random(100000);
			clientCreatedData.put(nameArray[arrayIndex] + id,
					nameArray[arrayIndex] + "Value" + id);

		}
		clientVersionOfServerData.putAll(clientCreatedData);

	}

	public synchronized void tellServerMapIsUpdated() throws IOException {

		Util.echo("going to tell server ....");
		Data data = new Data(this, "updateHashMap", clientCreatedData,
				clientPort);

		TellSomeBodySomething word = new TellSomeBodySomething(
				InetAddress.getByName("localhost"), NodeServer.serverPort, data);
//		word.connectSpeakDisconnect();
		word.connectKeepSpeakingDisconnect();
	}

	@Override
	public InetAddress getAddress() {

		try {
			return InetAddress.getByAddress(clientAddress);
		} catch (UnknownHostException e) {

			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Integer getPort() {

		return clientPort;
	}

}