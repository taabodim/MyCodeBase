/*
 * Copyright 2012 LMAX Ltd.
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


import com.admarketplace.isg.util.Util;
import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.support.ValueEvent;

public class EventPrinterWorkHandler implements WorkHandler<ValueEventAMP>
{
    private long total;

      public long getTotal()
    {
        return total;
    }

	@Override
	public void onEvent(ValueEventAMP event) throws Exception {
//		Util.echo("event is coming from ring buffer");
		
	}
}
