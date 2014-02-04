package com.admarketplace.offheap;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;

import sun.misc.Unsafe;

import com.admarketplace.isg.threads.ThreadPool;

public class BenchmarkingAtomcLongIncrement {
	static int poolSize = 1000;

	static ExecutorService benchPool = Executors.newFixedThreadPool(poolSize);
	static ExecutorService benchPoolAtomic = Executors.newFixedThreadPool(poolSize);

	private static Unsafe unsafe;
	static {
		Field singleoneInstanceField;
		try {
			singleoneInstanceField = Unsafe.class.getDeclaredField("theUnsafe");
			singleoneInstanceField.setAccessible(true);
			unsafe = (Unsafe) singleoneInstanceField.get(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static Runnable runnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub

			index.incrementAndGet();

		}
	};
	private static Object lock = new Object();
	private static Runnable runnableUnsafe = new Runnable() {
		@Override
		public void run() {
			boolean result = false;
			while (!result)
				result = unsafe.tryMonitorEnter(indexInt);

			if (result) {
				try {
					indexInt+=1;
				} finally {
					unsafe.monitorExit(indexInt);
				}
			}

		}
	};
	private static AtomicLong index = new AtomicLong(0);
	private static Integer indexInt = 0;

	public static void main(String args[]) {
		int runs= 200;
		for (int i = 0; i < runs; i++) {
			runUnsafeMonitorIncrementingIntegerBenchmark();
			runAtomicLongIncrementBenchmark();
			
//			indexInt=0;
			index=new AtomicLong(0);
		}
		benchPoolAtomic.shutdown();
		benchPool.shutdown();
	}

	public static void runUnsafeMonitorIncrementingIntegerBenchmark() {
	
//		System.out
//				.println("starting runUnsafeMonitorIncrementingIntegerBenchmark");
		long startTime = System.currentTimeMillis();

		int indexFinalNumber = 60000;
		Future<?>[] futures = new Future<?>[indexFinalNumber];

		for (int numberOfReqs = 0; numberOfReqs < indexFinalNumber; numberOfReqs++) {
			futures[numberOfReqs] = benchPool.submit(runnableUnsafe);
		}
//		System.out.println("unsafe is done with the update.");
		int timeToWaitForResult = 500;
		try {
			Thread.sleep(timeToWaitForResult);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(" indexInt is : " + indexInt);

		long endTime = System.currentTimeMillis();
		System.out
				.println(" finished runUnsafeMonitorIncrementingIntegerBenchmark in milis: "
						+ (endTime - startTime - timeToWaitForResult));

	}

	public static void runAtomicLongIncrementBenchmark() {
		int poolSize = 1000;
	
		System.out.println("starting runAtomicLongIncrementBenchmark");
		long startTime = System.currentTimeMillis();

		int indexFinalNumber = 60000;
		Future<?>[] futures = new Future<?>[indexFinalNumber];

		for (int numberOfReqs = 0; numberOfReqs < indexFinalNumber; numberOfReqs++) {
			futures[numberOfReqs] = benchPoolAtomic.submit(runnable);
		}
		for (int numberOfReqs = 0; numberOfReqs < indexFinalNumber; numberOfReqs++) {

			try {
				futures[numberOfReqs].get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

//		System.out.println("runAtomicLongIncrementBenchmark : index is : " + index);

		long endTime = System.currentTimeMillis();
		System.out.println("runAtomicLongIncrementBenchmark finished in milis: " + (endTime - startTime));

	}
}
