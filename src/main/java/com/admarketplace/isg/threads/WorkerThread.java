//package com.admarketplace.isg.threads;
//
///* this class will test how deamon threads work*/
//public class WorkerThread extends Thread {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		new WorkerThread().start();
//		try {
//			Thread.sleep(7500);
//		} catch (InterruptedException e) {
//		}
//		System.out.println("Main Thread ending");
//	}
//
//	public WorkerThread() {
//		setDaemon(false); // When false, (i.e. when it's a user thread),
//		// the Worker thread continues to run.
//		// When true, (i.e. when it's a daemon thread),
//		// the Worker thread terminates when the main
//		// thread terminates.
//	}
//
//	public void run() {
//		int count = 0;
//		while (true) {
//			System.out.println("Hello from Worker " + count++);
//			try {
//				sleep(5000);
//			} catch (InterruptedException e) {
//			}
//		}
//	}
//}
