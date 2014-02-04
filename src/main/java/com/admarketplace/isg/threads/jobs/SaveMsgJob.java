package com.admarketplace.isg.threads.jobs;

import com.admarketplace.isg.jms.MessageSender;
import com.admarketplace.isg.threads.JobListener;

import java.util.concurrent.atomic.AtomicLong;

public class SaveMsgJob extends MyJob {
	
	 

	private static AtomicLong msgNumber = new AtomicLong(0);

	public SaveMsgJob(String myName, JobListener myJobListener) {
		super(myName, myJobListener);
			
	}

	@Override
	public void run()  {
		msgNumber.incrementAndGet();
	
			
			
			try {
				
				System.out.println("sending msg....msgNumber : "+msgNumber);
			
				try {
					MessageSender msgSender = new MessageSender();
					msgSender.sendTextToQueue();
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			

				
			}
			finally{
				jobListener.jobDone(Thread.currentThread());
				
			}
		
		
		
	}

	@Override
	public int compareTo(Object obj) {
		SendMsgJob thatJob = (SendMsgJob) obj;

		if (timeOfCreation > thatJob.timeOfCreation)
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