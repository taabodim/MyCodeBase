package com.admarketplace.isg.mongo;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;

public class BasicDBListTest {
	public static void main(String[] args) {
	BasicDBObject mySubscription = new BasicDBObject();
	BasicDBList list = new BasicDBList();
	list.add("avc@cs.com");
	list.add("avc@cs1.com");
	list.add("avc@cs2.com");
	mySubscription.putAll(list);
	System.out.println(list.toString());
	}
}
