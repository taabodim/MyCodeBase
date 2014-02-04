package com.admarketplace.datacenter.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class DataExtractor {
	private final DataDao myDao;

	public DataExtractor(String daoType) {
		myDao = MyDataDao.getDaoInstance(daoType);
	}

	public HashMap<String,MyData> pullData(String dataCenterName) {
	
		return myDao.getAllData(dataCenterName);
	}

	public HashMap<String,MyData> merge(HashMap<String,MyData> dataFromA, HashMap<String,MyData> dataFromB) {
		Set<String> keySetA = dataFromA.keySet();
		Iterator<String> iteratorA = keySetA.iterator();
		
		
		Set<String> keySetB = dataFromB.keySet();
		Iterator<String> iteratorB = keySetB.iterator();
		
		
		HashMap<String,MyData>	totalMap = new HashMap<String,MyData>();
		
		while(iteratorA.hasNext())
		{
			String keyA = iteratorA.next();
		
			if(dataFromB.containsKey(keyA)==false)
			{//unique data in A
				totalMap.put(keyA, dataFromA.get(keyA));
			}
			else{//duplicate data
				
				MyData myDataFromA = dataFromA.get(keyA);
				MyData myDataFromB = dataFromB.get(keyA);
				MyData mergedData = myDataFromB.merge(myDataFromA);
				totalMap.put(keyA, mergedData);
			}
		}
		
		
		while(iteratorB.hasNext())
		{
			String keyB = iteratorB.next();
			if(dataFromA.containsKey(keyB)==false)
			{//unique data in A
				totalMap.put(keyB, dataFromB.get(keyB));
			}
			else{//duplicate data was already merged once
				
			}
		}
		return totalMap;
	}

}
