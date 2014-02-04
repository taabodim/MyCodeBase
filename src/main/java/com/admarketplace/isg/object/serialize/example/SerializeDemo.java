package com.admarketplace.isg.object.serialize.example;

import java.io.*;

class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1680942590496671998L;
	
	private String name;
	private String address;
	private String SSN;
	private String number;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSSN() {
		return SSN;
	}

	public void setSSN(String sSN) {
		SSN = sSN;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

}

public class SerializeDemo {
	public static void main(String[] args) {
	
		Employee e = new Employee();
		e.setName("Reyan Ali");
		e.setAddress("Phokka Kuan, Ambehta Peer");
		e.setSSN("11122333");
		e.setNumber("101");
		try {
			FileOutputStream fileOut = new FileOutputStream("C:/tmp/employee.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(e);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in /tmp/employee.ser");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
}

class DeserializeDemo {
	public static void main(String[] args) {
//		CopyOnWriteArrayList s;
//		SortedSet s1;
//		TreeSet s2;
//		Hashtable s3;
//		LinkedHashMap s4;
//		Locale s5;
//		PriorityQueue s6;
//		WeakHashMap s7;
//		Comparator  s8;
//		Comparable  s9;
//		Collections.synchronizedCollection(null);
//		
		Employee e = null;
		try {
			FileInputStream fileIn = new FileInputStream("C:/tmp/employee.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			e = (Employee) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Employee class not found");
			c.printStackTrace();
			return;
		}
		System.out.println("Deserialized Employee...");
		System.out.println("Name: " + e.getName());
		System.out.println("Address: " + e.getAddress());
		System.out.println("SSN: " + e.getSSN());
		System.out.println("Number: " + e.getNumber());
	}
}
