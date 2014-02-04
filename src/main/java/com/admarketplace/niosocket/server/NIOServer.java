package com.admarketplace.niosocket.server;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NIOServer {

	private static final long serialVersionUID = 7160582008593198318L;

	public static Integer serverPort = 9009;
	public static InetAddress serverAddress;
	public static ExecutorService executorService = Executors
			.newFixedThreadPool(50);

	private String nodeId = "NIONodeServer" + serverPort;

	public NIOServer() throws UnknownHostException {
		serverAddress = InetAddress.getByName("localhost");

	}

	public void startServer() throws Exception {
		NIOServerListeningService listenService = new NIOServerListeningService();
		new Thread(listenService).start();

	}

	public static void main(String args[]) throws Exception {
		NIOServer server = new NIOServer();
		server.startServer();

	}

}
