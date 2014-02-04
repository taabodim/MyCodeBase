package com.admarketplace.distributedhashmap.server;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

class Data<T,V> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8214732587282559148L;

	private ConcurrentHashMap<T,V> myData;
	private String command;
	private Integer clientPort;
	private Node node;// this will save the node that is sending the data across

	public Data(Node node, String command,
			ConcurrentHashMap<T,V> myMap, int port) {
		this.node = node;
		this.command = command;
		this.myData = myMap;
		this.clientPort = port;
	}

	// public void writeObject(ObjectOutputStream o) throws IOException
	// {
	// o.writeObject(node);//first the node
	// o.writeObject(myMap);//first the node
	// o.writeObject(command);//first the node
	// o.writeObject(clientPort);//first the node
	//
	// }
	//
	// public void readObject(ObjectInputStream o) throws IOException,
	// ClassNotFoundException
	// {
	// node = (Node) o.readObject();//first the node
	// myMap = (ConcurrentHashMap<String, String>) o.readObject();//first the
	// node
	// command = (String)o.readObject();//first the node
	// clientPort = (Integer)o.readObject();//first the node
	//
	// }
	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public int getClientPort() {
		return clientPort;
	}

	public void setClientPort(int clientPort) {
		this.clientPort = clientPort;
	}

	public ConcurrentHashMap<T,V> getMyData() {
		return myData;
	}

	public void setMyData(ConcurrentHashMap<T,V> myMap) {
		this.myData = myMap;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

}