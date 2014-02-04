package com.admarketplace.disruptor.example;

import static com.lmax.disruptor.RingBuffer.createMultiProducer;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.IgnoreExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.Sequence;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.TimeoutBlockingWaitStrategy;

import com.lmax.disruptor.WorkProcessor;



public class DisruptorPattern {
	private static int numPublishers = 1;
	private static int numProcessors = 1;
	private static int numWorkHandlers = 1;
	public static long numEventsToPublish = 100000000L;
//	public static long numEventsToPublish = 100L;
	private int bufferSize = 16*16*16;
	private final ExecutorService executor = Executors
			.newFixedThreadPool(numPublishers+numProcessors + 1);
	private final RingBuffer<MyDisruptorEvent> ringBuffer = createMultiProducer(
			MyDisruptorEvent.EVENT_FACTORY, bufferSize,
			new BusySpinWaitStrategy());
//			new TimeoutBlockingWaitStrategy(4,TimeUnit.SECONDS));
	private final SequenceBarrier sequenceBarrier;
	{
		sequenceBarrier = ringBuffer.newBarrier();
	}
	private final MyWorkHandler[] handlers = new MyWorkHandler[numWorkHandlers];
	
	private final Sequence workSequence = new Sequence(-1);

	private final WorkProcessor<MyDisruptorEvent>[] workProcessors = new WorkProcessor[numProcessors];

	public static void main(String args[]) {
		DisruptorPattern exmaple = new DisruptorPattern();
		exmaple.run();

	}

	public void run() {
		setupWorkHandlersAndWorkProcessors();
		try {
			setupPublishersAndStartPublishing();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void setupWorkHandlersAndWorkProcessors() {
		for (int i = 0; i < numWorkHandlers; i++) {
			handlers[i] = new MyWorkHandler();
		}
		for (int i = 0; i < numProcessors; i++) {
		workProcessors[i] = new WorkProcessor<MyDisruptorEvent>(ringBuffer,
				sequenceBarrier, handlers[i], new IgnoreExceptionHandler(),
				workSequence);
		}
	
	}

	private void setupPublishersAndStartPublishing() throws Exception {
		MyDisruptorPublisher myDisruptorPublisher[] = new MyDisruptorPublisher[numPublishers];
	
		for (int i = 0; i < numProcessors; i++) {
		ringBuffer.addGatingSequences(workProcessors[i].getSequence());
		   executor.submit(workProcessors[i]);
		}

		long startTime = System.currentTimeMillis();

		Future<?> futures = null;
		for (int i = 0; i < numPublishers; i++) {
			myDisruptorPublisher[i] = new MyDisruptorPublisher(ringBuffer);
			 futures = executor.submit(myDisruptorPublisher[i]);
		}
		for (int i = 0; i < numPublishers; i++) {
			
			 futures.get();//waiting for publishers to be done.
		}
		long endTime = System.currentTimeMillis() - startTime;
		System.out.println("published "+DisruptorPattern.numEventsToPublish +" in "+endTime+" milis ==> "+(DisruptorPattern.numEventsToPublish/endTime) + " msgs per milis ");
		System.out.println("published "+DisruptorPattern.numEventsToPublish +" in "+endTime+" milis ==> "+DisruptorPattern.numEventsToPublish+ " in "+(endTime/1000)+" sec & "+(endTime%1000)+" milis");



	}

}
