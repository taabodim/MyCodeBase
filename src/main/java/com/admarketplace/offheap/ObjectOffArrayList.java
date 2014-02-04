package com.admarketplace.offheap;

/*
 * Copyright 2012 LMAX Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.lang.reflect.Field;
import java.util.ArrayList;

import sun.misc.Unsafe;

/**
 * <p>
 * Concurrent sequence class used for tracking the progress of the ring buffer
 * and event processors. Support a number of concurrent operations including CAS
 * and order writes.
 * 
 * <p>
 * Also attempts to be more efficient with regards to false sharing by adding
 * padding around the volatile field.
 */
public class ObjectOffArrayList {

	/*******************************************************/
	// list of object fields
	private String name;
	private int nameSize = 50;
	private String id;
	private int idSize = 20;
	private String familyName;
	private int familyNameSize = 100;
	private Long ssId;
	private int ssIdSize = 1;
	/******************************************************/
	// list of offsets
	private int startOfObject = 0;
	private int nameOffset = startOfObject;
	private int idOffset = +(nameOffset + nameSize * 8);
	private int familyNameOffset = +(idOffset + idSize * 8);
	private int ssIdOffset = +(familyNameOffset + familyNameSize * 8);
	private int objectSize = ssIdOffset;
	/*******************************************************/

	private static Unsafe UNSAFE; // private static final long VALUE_OFFSET;
	static long base;
	static int scale;

	private static Unsafe getUnsafe() {
		Field unsafeField;
		try {
			unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
			unsafeField.setAccessible(true);
			UNSAFE = (Unsafe) unsafeField.get(null);
		} catch (Exception e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
		return UNSAFE;

	}

	static {
		UNSAFE = getUnsafe();
		scale = 16;
	}

	public static void runOffHeapTest() {
		// CharSequence str = new CharSequence();
		long startTime = System.currentTimeMillis();
		int num = 10000;
		System.out.println("before offheap: Free memory (bytes): "
				+ Runtime.getRuntime().freeMemory());
		long startFreeMemory = Runtime.getRuntime().freeMemory();
		for (int i = 0; i < num; i++) {

			// str.set(OffString.constStr.toCharArray()); }
			System.out.println("set is done"); // for (int i = 0; i < num; i++)
												// { //
			// System.out.println(str.get(i)); // }
			long offsetTime = System.currentTimeMillis() - startTime;
			long endFreeMemory = Runtime.getRuntime().freeMemory();
			System.out.println("after offheap : Free memory (bytes): "
					+ endFreeMemory);
			System.out.println("offheap took " + (offsetTime) + " millis");

			System.out.println("offheap took bytes  "
					+ (startFreeMemory - endFreeMemory));
		}
	}

	public static void main(String args[]) {
		runOffHeapTest();
	}

	private int index = 0;

	public ObjectOffArrayList() {

	}

	public void set(final char[] chars) {

		System.out.println("After allocate");
		for (long i = 0; i < chars.length; i++) {
			long address = base + (i * scale);

			UNSAFE.putChar(address, chars[(int) i]);
		} //
		System.out.println("After for"); // long copyOfbase = base;//you cant
		// pass the address anywhere.....make of copy of it!!! //
		OffStringAddrLength addrLength = new OffStringAddrLength(base, //
				chars.length);
		System.out.println("After add"); // //
		listOfStrings.add(addrLength);
		addrLength = null;
	}

	private static ArrayList<OffStringAddrLength> listOfStrings = new ArrayList<OffStringAddrLength>();

	class OffStringAddrLength {
		public OffStringAddrLength(long base, int len) {
			System.out.println("OffStringAddrLength construction");

			address = base;
			length = len;
		}

		public long address;
		public int length;
	}

	/**
	 * Perform a volatile read of this sequence's value.
	 * 
	 * @return The current value of the sequence.
	 */

	public String get(int strIndexInList) {
		// return UNSAFE.getLongVolatile(paddedValue, VALUE_OFFSET);
		base = listOfStrings.get(strIndexInList).address;
		int length = listOfStrings.get(strIndexInList).length;

		char[] string = new char[length];
		for (int i = 0; i < length; i++) {
			string[i] = UNSAFE.getChar(base + (i * scale));
		}
		return string.toString();
	}

	/**
	 * Perform an ordered write of this sequence. The intent is a Store/Store
	 * barrier between this write and any previous store.
	 * 
	 * @param value
	 *            The new value for the sequence.
	 */
	// public void set(final long value) {
	// UNSAFE.putOrderedLong(paddedValue, VALUE_OFFSET, value);
	// }

	/**
	 * Performs a volatile write of this sequence. The intent is a Store/Store
	 * barrier between this write and any previous write and a Store/Load
	 * barrier between this write and any subsequent volatile read.
	 * 
	 * @param value
	 *            The new value for the sequence.
	 */
	// public void setVolatile(final long value) {
	// UNSAFE.putLongVolatile(paddedValue, VALUE_OFFSET, value);
	// }

	/**
	 * Perform a compare and set operation on the sequence.
	 * 
	 * @param expectedValue
	 *            The expected current value.
	 * @param newValue
	 *            The value to update to.
	 * @return true if the operation succeeds, false otherwise.
	 */
	// public boolean compareAndSet(final long expectedValue, final long
	// newValue) {
	// return UNSAFE.compareAndSwapLong(paddedValue, VALUE_OFFSET,
	// expectedValue, newValue);
	// }

	/**
	 * Atomically increment the sequence by one.
	 * 
	 * @return The value after the increment
	 */
	// public long incrementAndGet() {
	// return addAndGet(1L);
	// }

	/**
	 * Atomically add the supplied value.
	 * 
	 * @param increment
	 *            The value to add to the sequence.
	 * @return The value after the increment.
	 */
	// public long addAndGet(final long increment) {
	// long currentValue;
	// long newValue;
	//
	// do {
	// currentValue = get();
	// newValue = currentValue + increment;
	// } while (!compareAndSet(currentValue, newValue));
	//
	// return newValue;
	// }

	// public String toString() {
	// return Long.toString(get());
	// }
}
