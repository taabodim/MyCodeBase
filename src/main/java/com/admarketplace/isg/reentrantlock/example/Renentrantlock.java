package com.admarketplace.isg.reentrantlock.example;

import com.admarketplace.isg.mypool.MongoConnection;
import com.admarketplace.isg.util.Util;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Renentrantlock {
	final ReentrantLock _lock = new ReentrantLock();
//	final Condition _aCondition = _lock.newCondition();
	
		private static CyclicBarrier barrier;

		public static void main(String[] args) {
			int numberOfThreads = 1000;
			
			barrier = new CyclicBarrier(numberOfThreads,new Runnable(){
	            @Override
	            public void run(){
	                //This task will be executed once all thread reaches barrier
	                System.out.println("All parties are arrived at barrier, lets play");
	            }
	        });
			for (int i = 0; i < numberOfThreads; i++) {
				MongoConnection conn = new MongoConnection();
				BarrierTestThread userThread = new BarrierTestThread(barrier, conn);
				userThread.start();

			}
		

		}

		private static class BarrierTestThread extends Thread {

			private MongoConnection conn;
			private CyclicBarrier barrier;

			public BarrierTestThread(CyclicBarrier barrier, MongoConnection conn) {
				this.conn = conn;
				this.barrier = barrier;
			}

			public void run() {
				conn.open();
				Util.echo(Thread.currentThread().getName() + " has opened a mongo connection");
				Util.echo(Thread.currentThread().getName() + " has reached the barrier");
				synchronized (this) {
					try {
					
						barrier.await(10,TimeUnit.SECONDS);
					} catch (Exception e) {

						e.printStackTrace();
					}
				}

				conn.close();
				Util.echo(Thread.currentThread().getName() + " has closed a mongo connection");

			}
		}

	

}
