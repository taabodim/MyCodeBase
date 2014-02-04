package com.admarketplace.isg.references.examples;

import com.admarketplace.isg.util.Util;

import java.lang.ref.Reference;

public class RunnableCleanUp implements Runnable {

	public void run() {
		while (true) {
			Reference reference;
			while ((reference = WeakInteger.queue.poll()) != null) {
				{
					Util.echo("removing reference");
					// call clean up here
					//
//					references.remove(reference);
				}
			}
		}
	}
}
