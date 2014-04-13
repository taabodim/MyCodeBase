package com.admarketplace.isg.cyclicalbarrier.example;

import com.admarketplace.isg.mypool.MongoConnection;
import com.admarketplace.isg.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class CyclicalBarrierTest {
	private static CyclicBarrier barrier;

	public static void main(String[] args) {
		int numberOfThreads = 1000;

		barrier = new CyclicBarrier(numberOfThreads, new Runnable() {
			@Override
			public void run() {
				// This task will be executed once all thread reaches barrier
				System.out
						.println("All parties are arrived at barrier, lets play");
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
			Util.echo(Thread.currentThread().getName()
					+ " has opened a mongo connection");
			Util.echo(Thread.currentThread().getName()
					+ " has reached the barrier");
			synchronized (this) {
				try {

					barrier.await(10, TimeUnit.SECONDS);
				} catch (Exception e) {

					e.printStackTrace();
				}
			}

			conn.close();
			Util.echo(Thread.currentThread().getName()
					+ " has closed a mongo connection");

		}
	}

}

class CustomCyclicalBarrier { //it runs the first half of batches then runs the second half of tasks
	private ReentrantLock lock;
	private AtomicInteger numberOfTask;
	private AtomicInteger numberOfDoneTasks;
	CustomCyclicalBarrier(int num)
	{
		if(num/2==1){
			
		}
		else{
			
		}
	}
	public void await() {
		if(numberOfDoneTasks.get()<=(numberOfTask.get()/2))
		{
			
		}
	}
	
	void test()
	{
		
		
		List<Runnable> allTasks = new ArrayList<Runnable>();
		MongoRunnerFactory factory = new MongoRunnerFactory();
		for(int i=0;i<10;i++)
		{
			
			allTasks.add(factory.createARunner());
			
		}
		
		//run all the runners in a thread pool
		//wait for half of them to run..then run the other off
	}
		
	

}

class MongoRunnerFactory {
	MongoThread createARunner()
	{
		Runnable mongoRunner =new Runnable(){
			public void run(){
				};
		
		};
		return (MongoThread) mongoRunner;
	}
}
class MongoThread extends Thread{
	CustomCyclicalBarrier barrier;
	MongoThread(CustomCyclicalBarrier br)
	{
		barrier = br;
	}
	public void run()
	{
		System.out.println("getting data from mongo db....Thread id : "+Thread.currentThread().getId());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		barrier.await();
	}
}