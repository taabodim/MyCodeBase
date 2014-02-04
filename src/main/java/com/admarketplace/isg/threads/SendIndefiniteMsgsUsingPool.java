package com.admarketplace.isg.threads;




import com.admarketplace.isg.jmx.StartTheMBean;
import com.admarketplace.isg.threads.jobs.SendMsgJob;
import com.admarketplace.isg.util.Util;



public class SendIndefiniteMsgsUsingPool {

/*we send an indefinite number of  messages and receive them using thread pool with 5 thread and using notify and wait mechanism*/
	
	public static void  main(String args[]) throws Exception
	{
		int poolSize = 200;//the number of threads should be optimally equal to number of 
		//processing cores +1
		int jobQueueLimit = 10000;//this limited queue will avoid OOM when we have too many jobs
		Long jobSize =(long) 250000000;
		
	
		StartTheMBean.main(null);
		ThreadPool pool = new ThreadPool(poolSize,jobQueueLimit);
      
		
		
		
		SendMsgJob sendMsgJob = new SendMsgJob("SendingMsgJob",pool);
		 pool.assignJob(sendMsgJob,jobSize);
		
		
		pool.latch.await();
		Util.echo("pool is empty now...Jobs remaining is "+pool.getRemainingJobsSize());
		
	
		pool.reportStatistics();
		pool.shutDown();
	
		
		
		
	}


}
