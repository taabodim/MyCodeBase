package com.admarketplace.isg.references.examples;

import com.admarketplace.isg.util.Util;

import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.concurrent.ThreadFactory;

public class WeakReferenceTest {

	static HashSet<WeakReference<Integer>> references = new HashSet<WeakReference<Integer>>();

	public static void main(String[] args) {

		ThreadFactory factory = new ThreadFactory() {

			@Override
			public Thread newThread(Runnable r) {

				Thread t = new Thread(r);
				t.setDaemon(true);
				return t;

			}
		};
		factory.newThread(new RunnableCleanUp()).start();
		for (int i = 0; i < 500000; i++) {
			Integer integer = new Integer(i);
			Util.echo("integer created " + i);
			WeakInteger weakRef = new WeakInteger(integer);
			integer = null;
			references.add(weakRef);

		}

	}
}