package com.admarketplace.unsafe;

import java.lang.instrument.Instrumentation;

public class ObjectSizeFetcher {
	
	
	//Add the following to your MANIFEST.MF:
	//Premain-Class: ObjectSizeFetcher
	
	//invoke with:
    //java -javaagent:ObjectSizeFetcherAgent.jar C
    private static Instrumentation instrumentation;

    public static void premain(String args, Instrumentation inst) {
        instrumentation = inst;
    }

    public static long getObjectSize(Object o) {
        return instrumentation.getObjectSize(o);
    }
    
    
   
}
