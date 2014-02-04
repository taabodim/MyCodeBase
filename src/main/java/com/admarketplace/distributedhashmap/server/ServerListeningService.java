package com.admarketplace.distributedhashmap.server;

import com.admarketplace.isg.util.Util;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListeningService implements Runnable {
	private static boolean serverShouldBeRunning = true;
	private ServerSocket serverSocket;

	public ServerListeningService() throws IOException {
		serverSocket = new ServerSocket(NodeServer.serverPort);
	}

	@Override
	public void run() {
		while (true) {
			try {

				Util.echo("Server is waiting for clients to talk.....");
				Socket connectionSocket = serverSocket.accept();

				HandlleClientRequestService listen = new HandlleClientRequestService(connectionSocket);
				listen.start();

			} catch (Exception e) {

				e.printStackTrace();

			}
		}

	}

}