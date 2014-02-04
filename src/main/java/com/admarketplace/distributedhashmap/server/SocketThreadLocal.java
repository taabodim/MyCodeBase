package com.admarketplace.distributedhashmap.server;

import java.net.Socket;

public class SocketThreadLocal extends ThreadLocal<Socket>{
	Socket mySocket;
	public SocketThreadLocal(Socket socket)
	{
		mySocket = socket;
	}
	
	@Override
	protected Socket initialValue()
	{
		
		return mySocket;
		
	}
	

}
