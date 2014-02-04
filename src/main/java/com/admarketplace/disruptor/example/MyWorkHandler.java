package com.admarketplace.disruptor.example;

import com.lmax.disruptor.WorkHandler;

public class MyWorkHandler  implements WorkHandler<MyDisruptorEvent>
{

    @Override
    public void onEvent(MyDisruptorEvent event) throws Exception
    {
        String value = event.getJsonDocument();
        System.out.println("Json doc is received to work handler. event id is "+event.getEventId()+" doc is "+value);
    }


}