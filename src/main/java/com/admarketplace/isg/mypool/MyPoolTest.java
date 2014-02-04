package com.admarketplace.isg.mypool;

import com.admarketplace.isg.util.Util;

import java.util.Set;
import java.util.concurrent.CountDownLatch;

public class MyPoolTest {
	public static void main(String args[]) throws Exception {
		
		int numberOfThreads = 10;
		PoolObjectFactory<MongoConnection> mongoFactory = new PoolObjectFactory<MongoConnection>(MongoConnection.class);
		MySimpleGenericPool<MongoConnection> pool = new MySimpleGenericPool<MongoConnection>(mongoFactory,2);
		CountDownLatch latch = new CountDownLatch(numberOfThreads);
		for (int i = 0; i < numberOfThreads; i++) {
			PoolUserThread userThread = new PoolUserThread(pool, latch);
			userThread.start();

		}
		latch.await();
		long allTheUses = 0;
		Set<Integer> keys = pool.poolOfObjects.keySet();
		for (Integer key : keys) {
			Util.echo("key = " + key + "  : numberOfAcquired " + pool.poolOfObjects.get(key).numberOfAcquired);
			allTheUses+=pool.poolOfObjects.get(key).numberOfAcquired.longValue();
		}
		Util.echo("All the uses is "+ allTheUses);
	}

}
