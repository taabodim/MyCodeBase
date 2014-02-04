package com.admarketplace.isg.threads;

import com.admarketplace.isg.jmx.StartTheMBean;
import com.admarketplace.isg.threads.jobs.SendMsgJobCallable;
import com.admarketplace.isg.util.Util;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicLong;

public class SendIndefiniteMsgsUsingExecutorService {

	/*
	 * we send an indefinite number of messages and receive them using thread
	 * pool with 5 thread and using notify and wait mechanism
	 */

	public static void main(String args[]) throws Exception {
		int poolSize = 200;// the number of threads should be optimally equal to
							// number of
		// processing cores +1
		int jobQueueLimit = 1000;// this limited queue will avoid OOM when we
									// have too many jobs
		Long jobSize = (long) 250000000;

		StartTheMBean.main(null);

		ArrayBlockingQueue<Callable<?>> sharedQueue = new ArrayBlockingQueue<Callable<?>>(jobQueueLimit);
		Object sharedLock = new Object();
		ExecutorServiceWrapper executorService = new ExecutorServiceWrapper(poolSize, sharedQueue, sharedLock,
				jobQueueLimit);
		executorService.start();

		for (int i = 0; i < jobSize; i++) {

			if (sharedQueue.size() >= jobQueueLimit) {

				synchronized (sharedLock) {
					Util.echo("sharedQueue is full, waiting for executorService to do the remaining jobs.");
					sharedLock.wait();
				}
			} else {
				Callable<?> task = new SendMsgJobCallable<AtomicLong>();
				sharedQueue.add(task);
				Util.echo("added a task to sharedQueue, queue size " + sharedQueue.size());
			}

		}// for loop
//			 ExecutorServiceWrapper.shutDwon();
	}
}
