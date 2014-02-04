package com.admarketplace.offheap;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class XMLFeedPartner implements Callable{
	private ArrayList<Long> in = new ArrayList<Long>();
	private volatile boolean partnerIsProcessing = true;
	public XMLFeedPartner(MyXMLFeedParam paramForThisReques) {
		// TODO Auto-generated constructor stub
	}
	@Override
	public Object call() throws Exception {
		while(partnerIsProcessing)
		{
			
		}
		return null;
	}
	

}
