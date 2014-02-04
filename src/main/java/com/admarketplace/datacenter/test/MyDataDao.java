package com.admarketplace.datacenter.test;

public class MyDataDao {

	private  MyDataDao ()
	{
		
	}
	public static DataDao getDaoInstance(String type)
	{
		if(type.equals("mongo"))
			return new MongoDao();
		if(type.equals("cache"))
			return new CacheDao();
		if(type.equals("file"))
			return new FileDao();
		if(type.equals("sql"))
			return new SqlDao();
	
		return null;
	}
}
