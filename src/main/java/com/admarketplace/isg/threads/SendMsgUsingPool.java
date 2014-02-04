package com.admarketplace.isg.threads;


import com.admarketplace.isg.threads.jobs.SendMsgJob;
import com.admarketplace.isg.util.Util;

import javax.jms.JMSException;



public class SendMsgUsingPool {

/*we send about 25000 messages and receive them using thread pool with 5 thread and using notify and wait mechanism*/
	public static void  main(String args[]) throws InterruptedException, JMSException
	{
		int poolSize = 5;//the number of threads should be optimally equal to number of 
		//processing cores +1
		int jobQueueLimit = 100;
		Long jobSize =(long) 250;
		

		ThreadPool pool = new ThreadPool(poolSize,jobQueueLimit);
		
		
		
		SendMsgJob sendMsgJob = new SendMsgJob("SendingMsgJob",pool);
		pool.assignJob(sendMsgJob,jobSize);
		
		
//		MessageReceiver msgReceiver = new MessageReceiver();
		pool.latch.await();
		Util.echo("pool is empty now...Jobs remaining is "+pool.getRemainingJobsSize());
		
	

		
		pool.reportStatistics();
		pool.shutDown();
		
	}


}
