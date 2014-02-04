package com.admarketplace.isg.mypool;

import java.util.concurrent.TimeUnit;

public interface GenericPool<R> {
	void open();
	public boolean isOpen();
	public void close();
	public void closeNow();
	public boolean add(R Resource);
	public boolean remove(R Resource);
	public boolean removeNow(R Resource);
	public R acquire() throws InterruptedException;
	public R acquire(long timeout, TimeUnit unit) throws InterruptedException;
	public void release(R resource);
}
