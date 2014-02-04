package com.admarketplace.isg.util.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class GenericObjectPoolTest {
	@SuppressWarnings("deprecation")
	public static void main(String args[]) throws Exception {
			int poolSize = 500;
		ExecutorService executor = Executors.newFixedThreadPool(poolSize);
		Object sharedLock = new Object();
	
			for (int key = 0; key < 10000; key++) {
				MongoConnectionThread myMoConnection = new MongoConnectionThread(key,sharedLock);
				if (myMoConnection.genericObjectPool == null) {
					myMoConnection.init();
				}
								executor.execute(myMoConnection);
				
			
			}

	
		executor.shutdown();
		while (!executor.isTerminated()) {
		}
//		System.out.println("genericKeyedObjectPool.getMaxTotal() is "
//				+ genericKeyedObjectPool.getMaxTotal());
//		System.out.println("Finished all threads");
//		System.out.println("genericKeyedObjectPool.getMaxTotal() is "
//				+ genericKeyedObjectPool.getMaxTotal());
//		System.out.println("genericKeyedObjectPool.getMaxTotal() is "
//				+ genericKeyedObjectPool.getMaxTotal());

	}
}
