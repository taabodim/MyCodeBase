package com.admarketplace.isg.semaphore.example;

import com.admarketplace.isg.mypool.MongoConnection;
import com.admarketplace.isg.util.Util;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;

public class MongoConnectionLimiter {

	private Semaphore semaphore;
	private AtomicLong numberOfOpenMongoConnecitons = new AtomicLong(0);

	public MongoConnectionLimiter(int permits) {
		semaphore = new Semaphore(permits);
	}

	public MongoConnection getAConnection() throws InterruptedException {
		semaphore.acquire();
		numberOfOpenMongoConnecitons.incrementAndGet();
		Util.echo("Number of Opened Connection is "+numberOfOpenMongoConnecitons.longValue());
		return new MongoConnection();

	}

	public void returnTheConnection(MongoConnection conn) {
		conn.close();
		Util.echo("mongoConnection was returend " );
		numberOfOpenMongoConnecitons.decrementAndGet();
		semaphore.release();
	}
}
