package com.admarketplace.datacenter.test;

import java.util.Comparator;

public class MyData implements Comparator<MyData>{
	private String name;
	private String revenue;
	private String date;
	
	public MyData(String name, String revenue, String date) {
		this.name = name;
		this.revenue = revenue;
		this.date = date;
		
	}

	public MyData() {
	}

	public String getName() {
		return name;
	}

	public String getRevenue() {
		return revenue;
	}

	public String getDate() {
		return date;
	}

	public boolean equals(MyData obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (this.name.equalsIgnoreCase(obj.name))
			return true;

		return false;
	}

	public MyData merge(MyData myDataFromA) {
		MyData mergedDate = new MyData();
		mergedDate.name = this.name;
		mergedDate.revenue = Integer.parseInt(this.revenue) + Integer.parseInt(myDataFromA.revenue) + "";
		mergedDate.date = this.date + ";" + myDataFromA.date;
		return mergedDate;
	}

	public String toString() {
		return "name : " + this.name + " revenue : " + this.revenue + " date : " + this.date;
	}

	@Override
	public int compare(MyData o1, MyData o2) {
	int f=-3;
//		if(DataCenterTest.sortKey.equalsIgnoreCase("revenue"))
	int revenue1 = Integer.parseInt(o1.getRevenue());
	int revenue2 = Integer.parseInt(o2.getRevenue());
			f = (revenue1 > revenue2 ? 0 : 1);
//		else
//			f = o1.getName().compareTo(o2.getName());
		System.out.println(" f is "+f);
		
		return f;
	}

}
