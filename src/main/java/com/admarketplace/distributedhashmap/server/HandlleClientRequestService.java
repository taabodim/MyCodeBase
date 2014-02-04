package com.admarketplace.distributedhashmap.server;

import com.admarketplace.isg.util.Util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class HandlleClientRequestService extends Thread {
	SocketThreadLocal connectionSocket;

	public HandlleClientRequestService(Socket socket) {
		connectionSocket = new SocketThreadLocal(socket);

	}

	private void recordClientInNodeMap(Node client) {

		NodeServer.nodesInNetwork.put(client.getNodeId(), client);

	}

	private synchronized void updateTheServerMap(
			ConcurrentHashMap<String, String> myMap) {
		Util.echo("Clustered Map is going to be updated . before update its size is "
				+ NodeServer.clusteredMap.values().size());

		NodeServer.clusteredMap.putAll(myMap);
		Util.echo("Clustered Map is updated . after update its size is "
				+ NodeServer.clusteredMap.values().size());

	}

	public void run() {
		Data myData = null;
		try {
			ObjectInputStream in = new ObjectInputStream(connectionSocket.get()
					.getInputStream());
			Util.echo("new client is coming in with this address : "
					+ connectionSocket.get().getInetAddress());
			boolean stopTheThread = false;

			while (!stopTheThread) {

				try {
					myData = (Data) in.readObject();
					workOnData(myData);
					Util.echo("command Data recieved is "
							+ myData.getCommand().toString());
					if (myData.getCommand().equalsIgnoreCase(
							Constants.ONE_TIME_CONVERSATION)) {// one time
																// conversation
						stopTheThread = true;

					}
					// }
				} catch (Exception e) {
					e.printStackTrace();
					in = new ObjectInputStream(connectionSocket.get()
							.getInputStream());
				}

				Thread.sleep(1000);

			}
			in.close();

			Util.echo("the end of handling request");
			connectionSocket.remove();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			NodeServer.nodesInNetwork.remove(myData.getNode().getNodeId());
		}
	}

	public void connectListenDisconnect() throws IOException,
			ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(connectionSocket.get()
				.getInputStream());

		Util.echo("client inteAddress : "
				+ connectionSocket.get().getInetAddress());

		Data myData = (Data) in.readObject();
		if (myData.getCommand().equalsIgnoreCase(
				Constants.ONE_TIME_CONVERSATION)) {// one time conversation

			in.close();

		}

		workOnData(myData);

		Util.echo("the end of handling request");

	}

	private void workOnData(Data myData) {
		Util.echo("Map Received from this client: \n"
				+ (myData.getMyData() != null ? myData.getMyData().toString()
						: "empty "));

		if (myData.getCommand().equals("updateHashMap")) {
			updateTheServerMap(myData.getMyData());

		}

		if (myData.getCommand().equals("IamListeningToThisPort")) {
			Util.echo("Server is recording the port of the client");
			recordClientInNodeMap(myData.getNode());

		}
	}

}
