package com.admarketplace.isg.mypool;

import com.admarketplace.isg.util.Util;

import java.util.concurrent.CountDownLatch;

public class PoolUserThread extends Thread {
	MySimpleGenericPool<MongoConnection> pool;
	CountDownLatch latch;

	public PoolUserThread(MySimpleGenericPool<MongoConnection> pool, CountDownLatch latch)

	{
		this.pool = pool;
		this.latch = latch;
	}

	public void run() {
		MongoConnection objectFromPool;
		try {

			objectFromPool = pool.acquire();

			Util.echo("object obtained from the pool hash code " + objectFromPool.hashCode());
			Thread.sleep(10);

			pool.release(objectFromPool);
			latch.countDown();
		} catch (InterruptedException e1) {

			e1.printStackTrace();
		}
	}

}
