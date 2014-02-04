import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

import com.admarketplace.isg.util.Util;


public class QueueTest {
	private static int NUM_PUBLISHERS=2;
	int NUM_CONSUMER = NUM_PUBLISHERS;
	int queueSize = 1024 * 64 * 16;
	
    private static final long numberOfObjects = 100000000;
	public static final CyclicBarrier cyclicBarrierForPublishers = new CyclicBarrier(NUM_PUBLISHERS+1);
	private final CyclicBarrier cyclicBarrierForConsumers = new CyclicBarrier(NUM_CONSUMER+1);
	private final ExecutorService executorPublisher = Executors.newFixedThreadPool(NUM_PUBLISHERS);
	private final ExecutorService executorConsumer = Executors.newFixedThreadPool(NUM_CONSUMER);
	private final BlockingQueue<Long> blockingQueue = new LinkedBlockingQueue<Long>(queueSize);
	  
	
	
	private final JsonQueuePublisher[] queuePublishers = new JsonQueuePublisher[NUM_PUBLISHERS];
	    {
	        for (int i = 0; i < NUM_PUBLISHERS; i++)
	        {
	            queuePublishers[i] = new JsonQueuePublisher(cyclicBarrierForPublishers, blockingQueue, numberOfObjects / NUM_PUBLISHERS);
	        }
	    }
	    
	    private final QueueConsumer[] queueConsumers = new QueueConsumer[NUM_CONSUMER];
		public static volatile boolean publishersAreDone = false;
	    {
	        for (int i = 0; i < NUM_CONSUMER; i++)
	        {
	        	queueConsumers[i] = new QueueConsumer(cyclicBarrierForConsumers, blockingQueue);
	        }
	    }
	    
	public static void main(String args[]) throws Exception {
		new QueueTest().runQueuePass();
	}
	
	private  void runQueuePass() throws Exception
    {
		
		
		
		new Thread(new Runnable(){

			@Override
			public void run() {
				try {
					consumeFromQueue();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}}).start();

		new Thread(new Runnable(){

			@Override
			public void run() {
				try {
					publishToQueue();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}}).start();
			
    }


	private void consumeFromQueue() throws Exception {
		
		long start = System.currentTimeMillis();
		 
		  Future<?>[] futures = new Future[NUM_CONSUMER];
	        
	        for (int i = 0; i < NUM_CONSUMER; i++)
	        {
	            futures[i] = executorConsumer.submit(queueConsumers[i]);
	           
	        }
	        cyclicBarrierForConsumers.await();
	        
	        for (int i = 0; i < NUM_CONSUMER; i++)
	        {
	           futures[i].get();//waiting for consumers to be done
	        }
	        
	        long opsPerSecond = (numberOfObjects) / ((System.currentTimeMillis() - start));
	        Util.echo("*!* END OF CONSUMING " +NUM_CONSUMER + " consumer  consumed "+numberOfObjects+" messages from  Queue in "+(System.currentTimeMillis() - start)+" milis it means "+opsPerSecond+ " messages/consuming per milisecond");
		    
		
	}

	private void publishToQueue() throws Exception {
		 long start = System.currentTimeMillis();
		 
		
	        Future<?>[] futures = new Future[NUM_PUBLISHERS];
	        for (int i = 0; i < NUM_PUBLISHERS; i++)
	        {
	        	 futures[i]= executorPublisher.submit(queuePublishers[i]);
	           
	        }
	        cyclicBarrierForPublishers.await();
	   
	        
	        for (int i = 0; i < NUM_PUBLISHERS; i++)
	        {
	           futures[i].get();//waiting for publisher to be done
	        
	        }
	        publishersAreDone =true;
	        
	        long opsPerSecond = (numberOfObjects) / ((System.currentTimeMillis() - start));
	        Util.echo(NUM_PUBLISHERS + " publisher  send "+numberOfObjects+" messages to  Queue in "+(System.currentTimeMillis() - start)+" milis it means "+opsPerSecond+ " messages/saving per milisecond");
	      
	}
}
