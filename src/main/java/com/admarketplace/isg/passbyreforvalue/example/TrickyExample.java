package com.admarketplace.isg.passbyreforvalue.example;

import java.awt.*;

public class TrickyExample {
	public static void tricky(Point arg1, Point arg2) {

		// java passing the copy of references
		// thats why the arg1 changes the values of pnt1 because
		// they point to the same memory address
		// but the swap fails because the temp is another reference
		// its the copy of reference to pnt1 not the original reference in main
		// method
		// so the copy of reference is assigned to args2
		// java is pass by value like C
		// java pass the value of references

		System.out.println("These are values passed from argument ");
		System.out.println("arg1.x: " + arg1.x + " arg1.y: " + arg1.y);
		System.out.println("changing values of the argument ");
		arg1.x = 100;
		arg1.y = 100;
		System.out.println("arg1.x: " + arg1.x + " arg1.y: " + arg1.y);

		Point temp = arg1;
		arg1 = arg2;
		arg2 = temp;
		System.out.println("swapping values inside the method");
		System.out.println("These are the swapped values.");

		System.out.println("X: " + arg1.x + " Y: " + arg1.y);
		System.out.println("X: " + arg2.x + " Y: " + arg2.y);

		System.out.println("end of method call");

		System.out.println("changing the temp value to see if it affects the arg1 outside method");
		temp.x = 98;
		temp.y = 97;
		System.out.println("These the temp values." + "X: " + temp.x + " Y: " + temp.y);

		// Only the method references are swapped, not the original ones
	}

	public static void main(String[] args) {
		Integer pnt1x = 10;
		Integer pnt1y = 20;

		Integer pnt2x = 30;
		Integer pnt2y = 40;

		Point pnt1 = new Point(pnt1x, pnt1y);
		Point pnt2 = new Point(pnt2x, pnt2y);
		System.out.println("X: " + pnt1.x + " Y: " + pnt1.y);
		System.out.println("X: " + pnt2.x + " Y: " + pnt2.y);
		System.out.println(" ");
		tricky(pnt1, pnt2);
		System.out.println("X: " + pnt1.x + " Y:" + pnt1.y);
		System.out.println("X: " + pnt2.x + " Y: " + pnt2.y);
	}
}
