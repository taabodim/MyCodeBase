package com.admarketplace.isg.util.pool;

import org.apache.commons.pool.PoolableObjectFactory;

public class MyGenericPoolableObjectFactory implements
		PoolableObjectFactory<MongoConnection> {
 private int num=0;
 private Object sharedLock;
 
 public MyGenericPoolableObjectFactory(Object mySharedLock)
 {
	 sharedLock = mySharedLock;
 }

	@Override
	public MongoConnection makeObject() throws Exception {
		
	
			synchronized (sharedLock) {
				num++;
				System.out.println("createing "+num+"th object");
				return new MongoConnection("no content yet");
			}
		
		
	}

	@Override
	public void destroyObject(MongoConnection obj) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean validateObject(MongoConnection obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void activateObject(MongoConnection obj) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void passivateObject(MongoConnection obj) throws Exception {
		// TODO Auto-generated method stub

	}

}
