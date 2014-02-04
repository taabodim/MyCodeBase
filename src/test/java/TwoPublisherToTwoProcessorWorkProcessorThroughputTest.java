/*
 * Copyright 2011 LMAX Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import static com.lmax.disruptor.RingBuffer.createMultiProducer;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.LockSupport;

import org.junit.Test;

import com.admarketplace.isg.util.Util;
import com.lmax.disruptor.AbstractPerfTestQueueVsDisruptor;
import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.IgnoreExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.Sequence;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.WorkProcessor;

/**
 * <pre>
 * Sequence a series of events from multiple publishers going to multiple work processors.
 * 
 * +----+                  +-----+
 * | P1 |---+          +-->| WP1 |
 * +----+   |  +-----+ |   +-----+
 *          +->| RB1 |-+
 * +----+   |  +-----+ |   +-----+
 * | P2 |---+          +-->| WP2 |
 * +----+                  +-----+
 * 
 * P1  - Publisher 1
 * P2  - Publisher 2
 * RB  - RingBuffer
 * WP1 - EventProcessor 1
 * WP2 - EventProcessor 2
 * </pre>
 */
public final class TwoPublisherToTwoProcessorWorkProcessorThroughputTest extends
		AbstractPerfTestQueueVsDisruptor {
	private static final int NUM_PUBLISHERS = 2;
	private static final int NUM_EVENT_HANDLERS = 4;
	private static final int BUFFER_SIZE = 1024 * 64 * 16;
	private static final long ITERATIONS = 100000000;
	private final ExecutorService executor = Executors
			.newFixedThreadPool(NUM_PUBLISHERS + 2);
	private final CyclicBarrier cyclicBarrier = new CyclicBarrier(
			NUM_PUBLISHERS + 1);

	// /////////////////////////////////////////////////////////////////////////////////////////////

	private final RingBuffer<ValueEventAMP> ringBuffer = createMultiProducer(
			new EventFactoryAMP(), BUFFER_SIZE, new BusySpinWaitStrategy());

	private final SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();
	private final Sequence workSequence = new Sequence(-1);

	private final EventPrinterWorkHandler[] handlers = new EventPrinterWorkHandler[NUM_EVENT_HANDLERS];
	{
		for (int i = 0; i < NUM_EVENT_HANDLERS; i++) {
			handlers[i] = new EventPrinterWorkHandler();
		}
	}

	@SuppressWarnings("unchecked")
	private final WorkProcessor<ValueEventAMP>[] workProcessors = new WorkProcessor[NUM_EVENT_HANDLERS];
	{
		for (int i = 0; i < NUM_EVENT_HANDLERS; i++) {
			workProcessors[i] = new WorkProcessor<ValueEventAMP>(ringBuffer,
					sequenceBarrier, handlers[i], new IgnoreExceptionHandler(),
					workSequence);
		}

	};

	private final RingBufferValuePublisher[] valuePublishers = new RingBufferValuePublisher[NUM_PUBLISHERS];
	{
		for (int i = 0; i < NUM_PUBLISHERS; i++) {
			valuePublishers[i] = new RingBufferValuePublisher(cyclicBarrier,
					ringBuffer, ITERATIONS);
		}

		ringBuffer.addGatingSequences(workProcessors[0].getSequence(),
				workProcessors[1].getSequence());
	}

	// /////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	protected int getRequiredProcessorCount() {
		return 4;
	}

	@Test
	@Override
	public void shouldCompareDisruptorVsQueues() throws Exception {
		testImplementations();
	}

	@Override
	protected long runQueuePass() throws Exception {
		return 0;
	}

	
	public static void main(String args[]) throws Exception {
		TwoPublisherToTwoProcessorWorkProcessorThroughputTest obj = new TwoPublisherToTwoProcessorWorkProcessorThroughputTest();
		obj.runDisruptorPassOriginal();
	}

	protected long runDisruptorPassOriginal() throws Exception {

		long expected = ringBuffer.getCursor() + (NUM_PUBLISHERS * ITERATIONS);
		Future<?>[] futures = new Future[NUM_PUBLISHERS];
		for (int i = 0; i < NUM_PUBLISHERS; i++) {
			futures[i] = executor.submit(valuePublishers[i]);
		}

		for (WorkProcessor<ValueEventAMP> processor : workProcessors) {
			executor.submit(processor);
		}

		long start = System.currentTimeMillis();
		cyclicBarrier.await();

		for (int i = 0; i < NUM_PUBLISHERS; i++) {
			futures[i].get();
		}

		while (workSequence.get() < expected) {
			LockSupport.parkNanos(1L);
		}

		long opsPerSecond = (ITERATIONS) / (System.currentTimeMillis() - start);
		Util.echo("Operation per second is " + opsPerSecond
				+ " per miliseconds");

		Thread.sleep(1000);

		for (WorkProcessor<ValueEventAMP> processor : workProcessors) {
			processor.halt();
		}

		return opsPerSecond;
	}

	@Override
	protected long runDisruptorPass() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
}
