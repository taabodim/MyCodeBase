package com.admarketplace.distributedhashmap.server;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NodeServer implements Node {

	private static final long serialVersionUID = 7160582008593198318L;
	public static ConcurrentHashMap<String, Node> nodesInNetwork = new ConcurrentHashMap<String, Node>();
	public static ConcurrentHashMap<String, String> clusteredMap = new ConcurrentHashMap<String, String>();

	public static Integer serverPort = 9006;
	public static InetAddress serverAddress;
	public static ExecutorService executorService = Executors
			.newFixedThreadPool(50);

	private String nodeId = "NodeServer" + serverPort;

	public NodeServer() throws UnknownHostException {
		serverAddress = InetAddress.getByName("localhost");

	}

	public void startServer() throws Exception {
		ServerListeningService listenService = new ServerListeningService();
		new Thread(listenService).start();
		
		DataReplicationService replicationService = new DataReplicationService();
		new Thread(replicationService).start();
		
	}

	public static void main(String args[]) throws Exception {
		NodeServer server = new NodeServer();
		server.startServer();

	}

	@Override
	public String getNodeId() {
		// TODO Auto-generated method stub
		return nodeId;
	}

	@Override
	public InetAddress getAddress() {

		return serverAddress;
	}

	@Override
	public Integer getPort() {

		return serverPort;
	}

}
