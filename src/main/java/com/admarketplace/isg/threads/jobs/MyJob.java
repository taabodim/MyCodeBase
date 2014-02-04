package com.admarketplace.isg.threads.jobs;

import com.admarketplace.isg.threads.JobListener;



public class MyJob implements Runnable,Comparable<Object>{
	
	protected JobListener jobListener;
	protected long timeOfCreation;
	protected String name;
	private static int msgNumber=1;

	public MyJob(String myName, JobListener myJobListener)
	{
		timeOfCreation = System.currentTimeMillis();
		name = myName;
		jobListener =myJobListener;
	}
	


	@Override
	public void run() {
		
	
			
			System.out.println("sending msg....msgNumber : "+msgNumber);
			msgNumber++;
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			finally{
				jobListener.jobDone(Thread.currentThread());
				
			}
		
		
		
	}

	@Override
	public int compareTo(Object obj) {
		MyJob thatJob = (MyJob) obj;
		
		if(timeOfCreation>thatJob.timeOfCreation)
			return 1;
		return 0;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
