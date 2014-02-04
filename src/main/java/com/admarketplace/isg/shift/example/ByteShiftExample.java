package com.admarketplace.isg.shift.example;

public class ByteShiftExample {

	public static void main(String[] args) {
//		Byte b = Byte.parseByte("00101011", 2);
//		System.out.println(b);
//		byte val = b.byteValue();
//		Byte shifted = new Byte((byte) (val >> 2));
//		System.out.println(shifted);

		// often overloked are the methods of Integer

		int i = Integer.parseInt("00101011", 2);
		System.out.println(Integer.toBinaryString(i));
		i >>= 1;
		System.out.println(Integer.toBinaryString(i));
		i >>= 1;
		System.out.println(Integer.toBinaryString(i));
		i >>= 1;
		System.out.println(Integer.toBinaryString(i));
		i >>= 1;
		System.out.println(Integer.toBinaryString(i));
		i >>= 1;
		System.out.println(Integer.toBinaryString(i));
		
	}

}
