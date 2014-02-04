package com.admarketplace.offheap;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class RequestProcessor {

	public static int requestPerSecond = 2000000;
	static ExecutorService pool = Executors.newFixedThreadPool(2000);
	static int requestQueueSize = 16 * 16 * 16 * 16;
	public static MyXMLFeedParam[] queueOfParams = new MyXMLFeedParam[(16 * 16 * 16 * 16) + 10];
	static {
		for (int i = 0; i < requestQueueSize; i++) {
			queueOfParams[i] = new MyXMLFeedParam();
			queueOfParams[i].clearState();
		}
	}

	public static void main(String args[]) {
		RequestComingIn httpReq = new RequestComingIn();
		Future<?>[] futures = new Future<?>[requestPerSecond];

		for (int i = 0; i < requestPerSecond; i++) {
			futures[i] = pool.submit(httpReq);
		}
		for (int i = 0; i < requestPerSecond; i++) {
			try {
				if (futures[i] != null)
					futures[i].get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			}
		}

		System.out.println("test finished ...");
		System.exit(0);
	}
}

class RequestComingIn implements Callable {
	
	private static AtomicInteger requestIndexInQueue = new AtomicInteger(-1);
	public static AtomicLong requestNumber = new AtomicLong(0);

	@Override
	public Integer call() {
		requestNumber.incrementAndGet();
		Thread.currentThread().setName("Thread-request-" + requestNumber.get());
		if (requestIndexInQueue.get() >= RequestProcessor.requestQueueSize) {
			requestIndexInQueue.set(0);
		}
		requestIndexInQueue.incrementAndGet();

		MyXMLFeedParam paramForThisReques=null;
//		try {
			paramForThisReques = RequestProcessor.queueOfParams[requestIndexInQueue
					.get()];
//		} catch (Throwable t) {
//			StringWriter sw = new StringWriter();
//			PrintWriter pw = new PrintWriter(sw);
//			t.printStackTrace(pw);
//			sw.toString();
//			System.out.println("error : for this 	requestIndexInQueue "
//					+ requestIndexInQueue);
//
//			System.exit(0);
//		}
		if (paramForThisReques.reuseMe == false) {

			System.out.println(" paramRequest " + requestIndexInQueue.get()
					+ " is being used by another Thread Request.");
			System.out.println(" creating a new Object!! ");
			paramForThisReques = new MyXMLFeedParam();
			// System.exit(0);

		} else if (paramForThisReques.reuseMe == true) {
			paramForThisReques.paramIndexInqueue = requestIndexInQueue.get();
			paramForThisReques.reuseMe = false;
			processRequest(paramForThisReques);
		}
		return 1;
	}

	private void processRequest(MyXMLFeedParam paramForThisReques) {
		try {
			// System.out.println(Thread.currentThread().getName()
			// + " processing on this param "
			// + paramForThisReques.paramIndexInqueue);
			executeFeed(paramForThisReques);
			Thread.sleep(100);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// processing takes about 10;
		finally {
			returnParamToPool(paramForThisReques);
		}
	}

	private void executeFeed(MyXMLFeedParam paramForThisReques) {
		XMLFeedPartner partner = new XMLFeedPartner(paramForThisReques);
		
	}

	private void returnParamToPool(MyXMLFeedParam paramForThisReques) {
		paramForThisReques.clearState();

	}
}
