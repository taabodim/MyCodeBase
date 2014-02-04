package com.admarketplace.isg.mongo;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class TestMongo {
	public static void main(String args[]) throws Exception {
		
//		MongoClientOptions options  = new MongoClientOptions();
		
		MongoClient mongoClient = new MongoClient("isgswntu6n1.nam.nsroot.net",27117);
		DB db = mongoClient.getDB("subscriptions");
		DBCollection collection = db.getCollection("subscriptions");
		DBCursor subscriptions = collection.find();
		while(subscriptions.hasNext())
		{
			echo("subscription name is "+subscriptions.next().get("Name"));
		}
	}

	private static void echo(String string) {
		System.out.println(string);
		
	}
}
