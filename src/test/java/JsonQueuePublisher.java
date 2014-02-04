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


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

import com.admarketplace.isg.util.Util;

public final class JsonQueuePublisher<T> implements Runnable
{
    private final CyclicBarrier cyclicBarrier;
    private final BlockingQueue<T> blockingQueue;
    private final long iterations;

    public JsonQueuePublisher(final CyclicBarrier cyclicBarrier, final BlockingQueue<T> blockingQueue, final long iterations)
    {
        
    	this.cyclicBarrier = cyclicBarrier;
        this.blockingQueue = blockingQueue;
        this.iterations = iterations;
    }

  
    public void run()
    {

        try
        {
        	Util.echo("Publisher as : "+Thread.currentThread().getName()+" is waiting on barrier number of waiting is "+cyclicBarrier.getNumberWaiting());
          	
            cyclicBarrier.await();
            Util.echo("Publisher barrier passed ");
            Util.echo("Publisher iterations is "+iterations);
            
        	
            for (long i = 0; i < iterations; i++)
            {
            	
            	blockingQueue.put((T) ObjectToMove.objValue);
//            	Util.echo("publishing object to queue.");
            }
            System.out.println(Thread.currentThread().getName() + " published "+(iterations) + " messages to the the blocking queue");
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }
        System.out.println(Thread.currentThread().getName() + " end of publishing. ");
        
    }
}
