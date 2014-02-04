package com.admarketplace.distributedhashmap.server;

import com.admarketplace.isg.util.Util;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class ClientNodeListeningService implements Runnable {

	private final ServerSocket clientSocket;

	public ClientNodeListeningService( ServerSocket socket) {
		clientSocket = socket;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		try {
		
//			Util.echo("client is listening on port " + clientSocket.getLocalPort());
			while (true) {

				Socket connectionSocket = clientSocket.accept();
				Util.echo("client inteAddress : "
						+ connectionSocket.getInetAddress());

				ObjectInputStream in = new ObjectInputStream(
						connectionSocket.getInputStream());
				Data myData = (Data) in.readObject();
				
				Util.echo("Command Received from this client: \n"
						+ myData.toString());

				if (myData.getCommand().equalsIgnoreCase(
						"getTheMostRecentClusteredMap")) {
					updateTheClientMapWithLatestClusteredMap(myData.getMyData());
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private synchronized void updateTheClientMapWithLatestClusteredMap(
			ConcurrentHashMap<String, String> myMap) {
		// TODO Auto-generated method stub
		Util.echo("myMap received from server is " + myMap.toString());
		Util.echo("client Map is going to be updated . before update its size is "
				+ NodeClient.clientVersionOfServerData.values().size());

		NodeClient.clientVersionOfServerData.putAll(myMap);
		Util.echo("client Map is updated . after update its size is "
				+ NodeClient.clientVersionOfServerData.values().size());

	}
}
