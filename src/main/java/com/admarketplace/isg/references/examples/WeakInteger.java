package com.admarketplace.isg.references.examples;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class WeakInteger extends WeakReference<Integer> {
	public static ReferenceQueue<? super Integer> queue = new ReferenceQueue();

	public WeakInteger(Integer integer) {
		super(integer, queue);
	}
}
