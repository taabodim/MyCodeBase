package com.admarketplace.isg.mypool;

import com.admarketplace.isg.util.Util;

public class MongoConnection extends Resource {
	
	public void open()
	{
		Util.echo("MongoConnection has been opened");
	}
	public MongoConnection()
	{
		Util.echo("MongoConnection obj has been constructed");
		isAvailable.set(true);
	}

	public void close() {
		Util.echo("MongoConnection has been closed");
	}

	
}
