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


import java.util.concurrent.CyclicBarrier;


import com.lmax.disruptor.RingBuffer;

public final class RingBufferValuePublisher<T extends ValueEventAMP> implements Runnable
{
    private final CyclicBarrier cyclicBarrier;
    private final RingBuffer<T> ringBuffer;
    private final long iterations;

    public RingBufferValuePublisher(final CyclicBarrier cyclicBarrier, final RingBuffer<T> ringBuffer, final long iterations)
    {
        this.cyclicBarrier = cyclicBarrier;
        this.ringBuffer = ringBuffer;
        this.iterations = iterations;
    }

    @Override
    public void run()
    {
        try
        {
            cyclicBarrier.await();

            for (long i = 0; i < iterations; i++)
            {
                long sequence = ringBuffer.next();
                T event = ringBuffer.get(sequence);
//               ValueEventAMP<String> obj = new ValueEventAMP<T>();
//               obj.setValue(ObjectToMove.objValue);
//                event.setValue(obj);
                ringBuffer.publish(sequence);
            }
        }
        catch (Exception ex)
        {
           ex.printStackTrace();
        }
    }
}
