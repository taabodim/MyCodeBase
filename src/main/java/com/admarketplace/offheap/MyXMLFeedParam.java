package com.admarketplace.offheap;

import java.util.concurrent.atomic.AtomicLong;

public class MyXMLFeedParam {
	public static AtomicLong numberOfObj =new AtomicLong(0);
	
	public MyXMLFeedParam()
	{
		numberOfObj.incrementAndGet();
		System.out.println(" number of MyXMLFeedParam created so far "+numberOfObj.get());
	}
 private String requestParameters;
	public int paramIndexInqueue;
	public boolean reuseMe = false;
	public void clearState() {
		reuseMe = true;
		
	}
 
}
