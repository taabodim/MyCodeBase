package com.admarketplace.isg.passbyreforvalue.example;

import java.awt.*;

public class TrickyExample {
	public static void tricky(Point arg1, Point arg2)
	{
		
		//java passing the copy of references
		//thats why the arg1 changes the values of pnt1 because 
		//they point to the same memory address
		//but the swap fails because the temp is another reference 
		//its the copy of reference to pnt1 not the original reference in main method
		//so the copy of reference is assigned to args2
		//java is pass by value like C
		//java pass the value of references 
	  arg1.x = 100;
	  arg1.y = 100;
	  Point temp = arg1;
	  arg1 = arg2;
	  arg2 = temp;
	  //Only the method references are swapped, not the original ones
	}
	public static void main(String [] args)
	{
	  Point pnt1 = new Point(0,0);
	  Point pnt2 = new Point(0,0);
	  System.out.println("X: " + pnt1.x + " Y: " +pnt1.y); 
	  System.out.println("X: " + pnt2.x + " Y: " +pnt2.y);
	  System.out.println(" ");
	  tricky(pnt1,pnt2);
	  System.out.println("X: " + pnt1.x + " Y:" + pnt1.y); 
	  System.out.println("X: " + pnt2.x + " Y: " +pnt2.y);  
	}
}
