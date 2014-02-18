package com.admarketplace.isg.interview.datastructures;

import com.admarketplace.isg.util.Util;

public class MyHashCodeFunction {

	// other methods would be in here

	public static void main(String[] args) {
		Employee employee = new Employee("hassan",123);
		Util.echo("hashCode is " + employee.hashCode());
	}
}

class Department {
}

class Employee {
	EmployeeId empId;

	public Employee(String name, int id) {
		empId = new EmployeeId(name, id);

	}

	class EmployeeId {
		int employeeId;
		String name;

		public EmployeeId(String n, int id) {
			name = n;
			employeeId = id;
		}
	}

	class EmployeeWorkExperience {
		int years;
		String major;
		String[] companies;
	}

	@Override
	public int hashCode() {
		int hash = 113;
		hash = hash * 43 + empId.employeeId;
		hash = hash * 23 + empId.name.hashCode();
		hash = hash * 11 + (dept != null ? 0 : dept.hashCode());

		return hash;
	}

	Department dept = new Department();

}