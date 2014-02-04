package com.admarketplace.datacenter.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class CacheDao implements DataDao {

	@Override
	public void add(MyData data) {
	}

	@Override
	public HashMap<String, MyData> getAllData(String dataCenterName) {

		HashMap<String, MyData> data = new HashMap<String, MyData>();
		HashMap<String, MyData> map = new HashMap<String, MyData>();

		if (dataCenterName.equalsIgnoreCase("dataCenterA")) {

			map = DataCenterTest.dataSoreA;
		}
		if (dataCenterName.equalsIgnoreCase("dataCenterB")) {

			map = DataCenterTest.dataSoreB;
		}
		Set<String> keySet = map.keySet();
		Iterator<String> iterator = keySet.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();

			data.put(key, map.get(key));
		}

		return data;
	}

	@Override
	public void update(MyData data) {
	}

	@Override
	public void delete(MyData data) {
	}

}
