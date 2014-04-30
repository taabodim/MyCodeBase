package com.admarketplace.thread.test;

import java.util.concurrent.atomic.AtomicLong;

import com.admarketplace.isg.util.Util;

public class TestingThreadVariables {

	

	public static void main(String args[]) {
		
		ThreadPeyKan model1 = new ThreadPeyKan("model1", "model1");
		ThreadPeyKan model2 = new ThreadPeyKan("model2", "model2");
		ThreadPeyKan model3 = new ThreadPeyKan("model3", "model3");
		ThreadPeyKan model4 = new ThreadPeyKan("model4", "model4");
		ThreadPeyKan model5 = new ThreadPeyKan("model5", "model5");
		ThreadPeyKan model6 = new ThreadPeyKan("model6", "model6");
		model1.start();
		model2.start();
		model3.start();
		model4.start();
		model5.start();
		model6.start();
	}
}
class ThreadPeyKan extends Thread {

	StringBuilder sharedContent = new StringBuilder("SharedContent : ");
    StringBuilder modelContent = new StringBuilder("");
	private long miles =0;

    static CommonPride commonPride = new CommonPride();
	ThreadPeyKan(String begContent, String nameOfThread) {
		Thread.currentThread().setName(nameOfThread);
		sharedContent.append(begContent);
		modelContent.append(begContent);
	}

	@Override
	public void run() {

	
	}
	
 void test1()
 //this will test that member variables of PeykanThread are thread safe
 //because on each instantiation they are created on a different place in heap
 //so for example the sharedContent that Thread6 accesses is a different 
 //object that the sharedContent that Thread5 or other threads access
 //so I dont need to synchronize access to that
 //but as commonPride object is static, all the threads change
 //the same miles parameter so its shown that its not thread safe
 
 {
	 
		for (long i = 0; i < 10000000L; i++) {
			miles++;
			commonPride.miles++;
			commonPride.milesAtomic.getAndIncrement();
			sharedContent.append(modelContent);
			commonPride.crash(modelContent.toString());
		}
		Util.echo(sharedContent.toString());
		Util.echo(commonPride.crashedWithMe.toString());
		Util.echo(modelContent+" : "+" milage is "+miles );
		Util.echo("Pride  : "+" milage Not threaded is "+commonPride.miles );
		Util.echo("Pride  : "+" milage Atomic is "+commonPride.milesAtomic );
	 
 }
}

class CommonPride {
	
	public StringBuilder crashedWithMe = new StringBuilder("Pride : ");
	public long miles;
	public AtomicLong milesAtomic = new AtomicLong(0);
	public void crash(String model)
	{
		crashedWithMe.append(model+";");
		
	}
	
}