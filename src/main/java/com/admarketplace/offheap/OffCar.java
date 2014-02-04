package com.admarketplace.offheap;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

public class OffCar {

	private long address;// address of each instanc in memory
	int sizeOffset = 8;
	private final int characterOffSET = 8;
	private int safety = 4;
	private String nameOfCar;

	private static Unsafe theUnsafe;
	static {
		Field singleoneInstanceField;
		try {
			singleoneInstanceField = Unsafe.class.getDeclaredField("theUnsafe");
			singleoneInstanceField.setAccessible(true);
			theUnsafe = (Unsafe) singleoneInstanceField.get(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void printString(long i) {

		System.out.println(" getOffString : " + getOffString());
	}

	public long createOffCar(String carName, int carNumber) {

		char[] charArray = carName.toCharArray();
		int stringSize = carName.length();
		address = theUnsafe.allocateMemory((carName.length() * 8) + safety);
		// System.out.println(" address : " + address);
		// System.out.println(" str.length() : " + str.length());

		theUnsafe.putInt(address, stringSize);

		for (int i = 0; i < carName.length(); i++) {
			putChar(address + sizeOffset + (i * characterOffSET), charArray[i]);
		}
		return address;
	}

	public OffCar() {
	}

	public OffCar(String constStr2) {
		nameOfCar = constStr2;

	}

	public String getOffString() {
		String str = "";
		int size = theUnsafe.getInt(address);
		System.out.println("size is " + size);
		for (int i = 0; i < size; i++) {
			char ch = theUnsafe.getChar(address + sizeOffset + (i * 8));
			System.out.println("char is : " + ch);
			str += ch;

		}
		return str;
	}

	public static void main(String args[]) {

		// OffString str = new OffString();
		// str.putChar('a');
		// System.out.println(str.printChar());
		OffString str = new OffString();
		long startTime = System.currentTimeMillis();
		int num = 1000000;
		// Long[] addressesOfStrings = new Long[num];
		System.out.println("before offheap: Free memory (bytes): "
				+ Runtime.getRuntime().freeMemory());
		long startFreeMemory = Runtime.getRuntime().freeMemory();
		for (int i = 0; i < num; i++) {
			// addressesOfStrings[i] =
			str.createOffString(constStr);
		}
		// for (int i = 0; i < num; i++) {
		// str.printString(addressesOfStrings[i]);
		// }
		long offsetTime = System.currentTimeMillis() - startTime;
		long endFreeMemory = Runtime.getRuntime().freeMemory();
		System.out.println("after offheap : Free memory (bytes): "
				+ endFreeMemory);
		System.out.println("offheap took " + (offsetTime) + " millis");
		System.out.println("offheap took bytes  "
				+ (startFreeMemory - endFreeMemory));

		// runHeapTest();


		 startTime = System.currentTimeMillis();
		
		for (long i = 0; i < num; i++) {

			Car strObj = new Car(constStr);

		}
		System.out.println("took " + (System.currentTimeMillis() - startTime)
				+ " millis");
		long endFreeMemoryHeap = Runtime.getRuntime().freeMemory();
		System.out.println("heap took bytes  "
				+ ( endFreeMemory-endFreeMemoryHeap));

	}

	public static void runHeapTest() {
		int num = 1000;

		long startTime = System.currentTimeMillis();
		long startFreeMemory = Runtime.getRuntime().freeMemory();

		for (long i = 0; i < num; i++) {

			Car strObj = new Car(constStr);

		}
		System.out.println("took " + (System.currentTimeMillis() - startTime)
				+ " millis");
		long endFreeMemory = Runtime.getRuntime().freeMemory();
		System.out.println("heap took bytes  "
				+ (startFreeMemory - endFreeMemory));

	}

	private char printChar() {
		// TODO Auto-generated method stub
		return theUnsafe.getChar(address);
	}

	private void putChar(long addressofchar, char character) {
		theUnsafe.putChar(addressofchar, character);

	}

	public static String constStr = "hassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassan"
			+ "hassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassan"
			+ "hassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassan"
			+ "hassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassan"
			+ "hassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassan"
			+ "hassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassan"
			+ "hassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassan"
			+ "hassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassanhassan";

}
