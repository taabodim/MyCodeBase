package com.admarketplace.isg.jmx;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class StartTheMBean {
	/*run this program with these vm arguments
	-Dcom.sun.management.jmxremote.port=9999  
	-Dcom.sun.management.jmxremote.authenticate=false 
	-Dcom.sun.management.jmxremote.ssl=false
	
	and 
	go to jconsole in the remote field , type  localhost:9999  and connect
	*/
	public static void main(String args[]) throws Exception {
		 MBeanServer mbs = ManagementFactory.getPlatformMBeanServer(); 
	        ObjectName name = new ObjectName("com.example:type=Hello"); 
	        Hello mbean = new Hello(); 
	        mbs.registerMBean(mbean, name); 
	        System.out.println("Waiting forever..."); 
//	        Thread.sleep(Long.MAX_VALUE); 
	}
}
