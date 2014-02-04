package com.admarketplace.isg.threads;

import com.admarketplace.isg.threads.jobs.SendMsgJob;
import com.admarketplace.isg.util.Util;

public class SavingMsgInMongoToBeDoneLater {

	public static void  main(String args[]) throws Exception
	{
		int poolSize = 5;
		int jobQueueLimit = 100;
		Long jobSize =(long) 250;
		Object myLock = new Object();
		ThreadPool pool = new ThreadPool(poolSize,jobQueueLimit);
		
		
		
		SendMsgJob sendMsgJob = new SendMsgJob("SendingMsgJob",pool);
//	
		pool.assignJob(sendMsgJob,jobSize );
		
		pool.latch.await();
		Util.echo("pool is empty now...Jobs remaining is "+pool.getRemainingJobsSize());
		
	

		
		pool.reportStatistics();
		
	}
}
