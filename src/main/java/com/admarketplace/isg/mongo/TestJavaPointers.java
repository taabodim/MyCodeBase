package com.admarketplace.isg.mongo;

//this class tests passing the references
//java is always pass by value
public class TestJavaPointers {

	public static void main(String args[]) {

	
		Integer myA = 200;
		Integer newA = myA;
		newA=21;
		
		System.out.println("A is "+myA);
		System.out.println("newA is "+newA);
		
		
	}

	
}
