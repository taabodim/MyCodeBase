package com.admarketplace.isg.references.examples;

import com.admarketplace.isg.util.Util;

import java.lang.ref.ReferenceQueue;
import java.util.LinkedList;

public class PhantomReferenceTest {
	
	private static ReferenceQueue<Foo> fooRefQueue = new ReferenceQueue<Foo>();
	private static LinkedList<FooPhantomReference> phantomReferences = new LinkedList<FooPhantomReference>();
	
	public static void main(String[] args) throws InterruptedException {
		Util.printProcessId();
		referenceThread.setDaemon(true);
		referenceThread.start();

	
		PhantomReferenceTest test = new PhantomReferenceTest();
		test.fireFoo();
		
	}

	public  void fireFoo() throws InterruptedException {
		
		for (int i = 0; i < 100; i++) {
			Foo lazyFoo = new Foo(i);
			lazyFoo.getFired();
			
			FooPhantomReference fooPhantom = new FooPhantomReference(lazyFoo, fooRefQueue);
			fooPhantom.enqueue();
	
			Thread.sleep(1000);


		}
	}

	


	final static Thread referenceThread = new Thread() {
		public void run() {
			while (true) {
				
				try {
					Thread.sleep(1500);
					FooPhantomReference ref;
					do {
				
					ref = (FooPhantomReference) fooRefQueue.remove();
					ref.cleanup();
					}while(ref!=null);
				
				} catch (Exception ex) {
					// log exception, continue
				}
			}
		}
	};


}
