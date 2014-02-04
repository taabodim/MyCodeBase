package com.admarketplace.isg.references.examples;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class FooPhantomReference extends PhantomReference<Foo> {
	Foo referent;
	public FooPhantomReference(Foo referent, ReferenceQueue<Foo> q) {
		super(referent, q);
		this.referent=referent;
	}
	 public void cleanup() {
		 referent.cleanUp();
     }

}

