package com.admarketplace.datacenter.test;

import java.util.HashMap;

public interface DataDao {
	 public void add(MyData data);
	 public HashMap<String,MyData>  getAllData(String dataCenterName);
	 public void update(MyData data);
	 public void delete(MyData data);
	
}
