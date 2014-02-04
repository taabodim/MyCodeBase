package com.admarketplace.isg.util.pool;

public class MongoConnection {
	private String content;
	private Integer key;

	public MongoConnection(Integer myKey, String myContent) {
		content = myContent;
		key = myKey;
	}

	public MongoConnection(String myContent) {
		content = myContent;
		 key = -1;
	}

	public synchronized void printContent() throws InterruptedException {
		if (key > 0) {
			System.out.println("key is " + key + " , content is " + content);

		} else {
			System.out.println(" content is " + content);

		}

	}

}
