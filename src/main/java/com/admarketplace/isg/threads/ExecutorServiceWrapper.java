package com.admarketplace.isg.threads;

import com.admarketplace.isg.util.Util;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//This class wraps the executor service to process the jobs in batches, to avoid OOM in case of 
//executing a huge number of jobs
public class ExecutorServiceWrapper {

	private ArrayBlockingQueue<Callable<?>> sharedQueue;
	private static ExecutorService executorService = null;
	private Object sharedLock;
	private static boolean shutDown;
	private int jobQueueLimit;
	private static Thread internalExecutorServiceRunnerThread;

	public ExecutorServiceWrapper(int poolSize, ArrayBlockingQueue<Callable<?>> mySharedQueue, Object mySharedLock,
			int myJobQueueLimit) {
		if (executorService == null) {
			executorService = Executors.newFixedThreadPool(poolSize);
		}
		sharedLock = mySharedLock;
		sharedQueue = mySharedQueue;
		jobQueueLimit = myJobQueueLimit;
	}

	public void start() throws InterruptedException {
		Runnable runTheExecutorServiceThread = new Runnable() {

			@Override
			public void run() {
				while (true) {

					if (sharedQueue.size() < jobQueueLimit / 10) {

						synchronized (sharedLock) {
							Util.echo("ExecutorServiceWrapper : signaling the producer thread in for loop to fill the queue, queue size is "
									+ sharedQueue.size());
							sharedLock.notify();// notify the related thread to
												// fill up
												// the queue to the limit
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								
								e.printStackTrace();
							}
						}

					}
					if (sharedQueue.size() > 0) {
						while (sharedQueue.size() > 0) {
							// submit all to the executor
							Util.echo("ExecutorServiceWrapper : submmitted a task to the Service , queue size is "
									+ sharedQueue.size());
							Callable<?> task = sharedQueue.poll();
							executorService.submit(task);

						}
						if (sharedQueue.size() == 0 && shutDown == true)
							break;

					}
				}// while
				Util.echo("ExecutorServiceWrapper : Shutting down the executor Service.");
				executorService.shutdown();
			}
		};
		internalExecutorServiceRunnerThread = new Thread(runTheExecutorServiceThread);
		internalExecutorServiceRunnerThread.start();

	}

	public static void shutDwon() {
		ExecutorServiceWrapper.shutDown = true;
		if (internalExecutorServiceRunnerThread != null)
			internalExecutorServiceRunnerThread.interrupt();
	}

}
