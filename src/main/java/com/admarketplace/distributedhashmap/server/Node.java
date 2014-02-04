package com.admarketplace.distributedhashmap.server;

import java.io.Serializable;
import java.net.InetAddress;

public interface Node extends Serializable{

	public String getNodeId();
	public InetAddress getAddress();
	public Integer getPort();
  
}
