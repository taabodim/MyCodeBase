import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;

import com.admarketplace.isg.util.Util;


public final class QueueConsumer<T> implements Runnable
{
    private final CyclicBarrier cyclicBarrier;
    private final BlockingQueue<T> blockingQueue;
  

    public QueueConsumer(final CyclicBarrier cyclicBarrier, final BlockingQueue<T> blockingQueue){
        
    	this.cyclicBarrier = cyclicBarrier;
        this.blockingQueue = blockingQueue;
       
    }

  
    public void run()
    {
    	
    	
        try
        {
        	Util.echo("Consumer : "+Thread.currentThread().getName()+" is waiting on barrier number of waiting is "+cyclicBarrier.getNumberWaiting());
            
            cyclicBarrier.await();
            while(!QueueTest.publishersAreDone)
            	{
            	 String obj = (String) blockingQueue.poll();
//                 Util.echo("point consuming from queue");
            }
            System.out.println(Thread.currentThread().getName() + " consumed " + "many? messages from the the blocking queue");
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }
    }
}
