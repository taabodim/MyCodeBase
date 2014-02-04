package com.admarketplace.isg.util.pool;

import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;

public class MongoConnectionThread extends Thread {

	public static GenericObjectPool<MongoConnection> genericObjectPool = null;
	private static PoolableObjectFactory<MongoConnection> myGenericObjectPoolFactory = null;

	private Integer key;
	private Object sharedLock;

	public MongoConnectionThread(Integer myKey,Object mySharedLock) {
		key = myKey;
		sharedLock = mySharedLock;
	}

	public void init() {
		genericObjectPool = new GenericObjectPool<MongoConnection>();
		myGenericObjectPoolFactory = new MyGenericPoolableObjectFactory(sharedLock);
		genericObjectPool.setFactory(myGenericObjectPoolFactory);
	}

	public void run() {

		

		try {
			
				
				MongoConnection myMoConnection = genericObjectPool
						.borrowObject();
				System.out.println(genericObjectPool.getNumActive()+" Objects borrowed from pool ");
				myMoConnection.printContent();
				Thread.sleep(100);
				genericObjectPool.returnObject(myMoConnection);
		
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}
