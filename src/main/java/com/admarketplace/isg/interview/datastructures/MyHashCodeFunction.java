package com.admarketplace.isg.interview.datastructures;

public class MyHashCodeFunction {
	int employeeId;
	String name;
	Department dept;

	// other methods would be in here

	@Override
	public int hashCode() {
		// int hash = 1;
		// hash = hash * 17 + employeeId;
		// hash = hash * 31 + name.hashCode();
		// hash = hash * 13 + (dept == null ? 0 : dept.hashCode());
		int hash = 113;
		hash = hash * 43 + employeeId;
		hash = hash * 23 + name.hashCode();
		hash = hash * 11 + (dept != null ? 0 : dept.hashCode());
		
		return hash;
	}
}

class Department {
}