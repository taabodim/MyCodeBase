package com.admarketplace.datacenter.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

public class DataCenterTest {
  public static String sortKey ="revenue";
  
	public static HashMap<String, MyData> dataSoreA;
	public static HashMap<String, MyData> dataSoreB;

	public static void main(String[] args) {

		makeCacheDataStore("dataCenterA");
		makeCacheDataStore("dataCenterB");
		// fetch data from data center A
		// fetch data from data center B
		DataExtractor dataExtractor = new DataExtractor("cache");
		HashMap<String, MyData> dataFromA = dataExtractor.pullData("dataCenterA");
		HashMap<String, MyData> dataFromB = dataExtractor.pullData("dataCenterB");
		HashMap<String, MyData> mergedData = dataExtractor.merge(dataFromA, dataFromB);
		// merge data
		TreeMap<String, MyData> treeMapSortedByName = new TreeMap<String, MyData>();
		treeMapSortedByName.putAll(mergedData);
			
		printReport(treeMapSortedByName);
		// print report

	}

	private static void makeCacheDataStore(String name) {
		MyData john = new MyData("john", "200", "10/10/2011");
		MyData peter = new MyData("peter", "300", "10/10/2011");
		MyData mike = new MyData("mike", "500", "10/10/2011");
		MyData ann = new MyData("ann", "2000", "10/10/2011");

		MyData dann = new MyData("dann", "200", "10/10/2011");

		dataSoreA = new HashMap<String, MyData> ();
		dataSoreA.put("john", john);
		dataSoreA.put("peter", peter);
		dataSoreA.put("mike", mike);
		dataSoreA.put("dann", dann);
		
		 dataSoreB = new HashMap<String, MyData> ();
		
		dataSoreB.put("john", john);
		dataSoreB.put("peter", peter);
		dataSoreB.put("ann", ann);
	
	}

	private static void printReport(TreeMap<String, MyData> mergedData) {
		Set<String> keySet = mergedData.keySet();
		Iterator<String> iterator = keySet.iterator();
		
		System.out.println(" PROGRAM OUTPUT ------------------");
		while (iterator.hasNext()) {
			String key = iterator.next();
			System.out.println(" key : " + key + " ---> " + mergedData.get(key).toString());

		}
	}
}
