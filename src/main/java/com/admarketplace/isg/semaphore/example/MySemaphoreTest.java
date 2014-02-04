package com.admarketplace.isg.semaphore.example;

import com.admarketplace.isg.mypool.MongoConnection;
import com.admarketplace.isg.util.Util;

import java.util.concurrent.CountDownLatch;

/*
 * 
 * Mutexes are typically used to serialise access to a section of  
 * re-entrant code that cannot be executed concurrently by more than one thread. A mutex object only allows one thread into a controlled section, forcing other threads which attempt to gain access to that section to wait until the first thread has exited from that section."
 * */

/*
 * A semaphore restricts the number of simultaneous users of a shared
 *  resource up to a maximum number. Threads can request access to the resource (decrementing the semaphore), and can signal that they have finished using the resource (incrementing the semaphore).
 * */
public class MySemaphoreTest {
	MongoConnectionLimiter mongoLimiter = new MongoConnectionLimiter(10);
	 
	public static void main(String args[]) throws Exception {
		int numberOfThreads = 1000;
		
		MySemaphoreTest test = new MySemaphoreTest();
		
		CountDownLatch latch = new CountDownLatch(numberOfThreads);
		for (int i = 0; i < numberOfThreads; i++) {
			MongoUser userThread = new MongoUser(test.mongoLimiter, latch);
			userThread.start();

		}
		latch.await();
		
		
	}
	private static class MongoUser extends Thread {
		MongoConnectionLimiter limitedMongo;
		CountDownLatch latch;
		public MongoUser(MongoConnectionLimiter limitedMongo ,CountDownLatch latch)

		{
			this.limitedMongo = limitedMongo;
			this.latch = latch;
		}

		public void run() {
			MongoConnection mongoConnection = null;
			try {
				mongoConnection = limitedMongo.getAConnection();
			} catch (InterruptedException e1) {
				
				e1.printStackTrace();
			}
			Util.echo("mongoConnection was obtained from limiter" );
			synchronized (this){
				try {
					Thread.currentThread().wait(100);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
		
			if(mongoConnection!=null) 
				limitedMongo.returnTheConnection(mongoConnection);
			latch.countDown();
		}
		}

}
