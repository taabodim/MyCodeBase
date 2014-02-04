package com.admarketplace.isg.mypool;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public abstract class Resource {
	public Resource() {
		numberOfAcquired.set(0);
		isAvailable.set(true);

	}

	protected AtomicBoolean isAvailable = new AtomicBoolean();

	public boolean isAvailable() {

		return isAvailable.get();

	}

	public void setAvailable(boolean b) {
		isAvailable.set(b);
	}

	protected AtomicLong numberOfAcquired = new AtomicLong(0);
}
