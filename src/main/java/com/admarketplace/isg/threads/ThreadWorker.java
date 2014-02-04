package com.admarketplace.isg.threads;

import com.admarketplace.isg.threads.jobs.MyJob;
import com.admarketplace.isg.util.Util;

import java.util.PriorityQueue;
/*this class is the worker for the ThreadPool class */
public class ThreadWorker extends Thread {

	private int numberOfJobDoneByThread = 0;

	public boolean isStopped = false;
	private Object sharedLock;
	private PriorityQueue<MyJob> sharedJobQueue;



	public int getNumberOfJobDoneByThread() {
		return numberOfJobDoneByThread;
	}

	

	public ThreadWorker(String myName, MyJob job, Object mysharedLock, PriorityQueue<MyJob> mySharedJobQueue) {
		sharedJobQueue = mySharedJobQueue;
		
		Thread.currentThread().setName(myName);
		sharedLock = mysharedLock;
		if (job != null)
			echo("ThreadWorker has been created with this Job " + job.getName());

	}

	public void run() {
		MyJob r = null;

		while (!isStopped) {
			Util.echo("thread " + getName() + " : running");

			if (!sharedJobQueue.isEmpty()) {
				if (sharedJobQueue != null) {
					r = sharedJobQueue.poll();
					if (r != null) {

						r.run();
						if (sharedJobQueue.size() < ThreadPool.limitOfJobQueue / 2) {
							synchronized (sharedLock) {
								sharedLock.notifyAll();// notify the thread pool
														// to
														// refill the jobqueue
							}
						}
					}
				} else {
					Util.echo("error: shared job queue is null.");
				}
			} else {
				
					synchronized (sharedLock) {
						Util.echo("thread " + getName() + " : ThreadPool.jobQueue.size() < 0");
						Util.echo("thread " + getName() + " : waiting");

					try {
							sharedLock.wait();
						} catch (InterruptedException e) {
							
							e.printStackTrace();
						}
					}

			
			}

			numberOfJobDoneByThread++;
		}// while(true)

	}

	private void echo(String str) {
		System.out.println(str);

	}

}
