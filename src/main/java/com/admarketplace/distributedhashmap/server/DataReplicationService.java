package com.admarketplace.distributedhashmap.server;

import com.admarketplace.isg.util.Util;

import java.io.IOException;
import java.util.Set;

//this service will find all the nodes in network
//and will replicate the distributed server data to their data

public class DataReplicationService implements Runnable {

	@Override
	public void run() {
		while (true) {
			Set<String> keySet = NodeServer.nodesInNetwork.keySet();
			Util.echo("ReplicationService : number of client nodes is : "
					+ keySet.size());
			for (String key : keySet) {
				Data myFullData = new Data(null,
						"getTheMostRecentClusteredMap",
						NodeServer.clusteredMap, -1);
				Node node = NodeServer.nodesInNetwork.get(key);
				Util.echo("DataReplicationServices :  node properties are , address + port "
						+ node.getAddress() + " " + node.getPort());
				try {
					sendDataBackToThisClient(node, myFullData);
				} catch (IOException e) {

					e.printStackTrace();
				}

				Util.echo("Server replicating data  to clientNode id : "
						+ node.getNodeId());

			}
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
		
	}

	public void sendDataBackToThisClient(Node node, Data data)
			throws IOException {
		Util.echo("replicationService sent data to this node , address + port "
				+ node.getAddress() + " " + node.getPort());
		TellSomeBodySomething word = new TellSomeBodySomething(
				node.getAddress(), node.getPort(), data);
		word.connectSpeakDisconnect();
	}
}
