package com.admarketplace.unsafe;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import sun.misc.Unsafe;

public class OffHeapObjectList {
	public static final Unsafe unsafe;

	static {
		try {
			Field field = Unsafe.class.getDeclaredField("theUnsafe");
			field.setAccessible(true);
			unsafe = (Unsafe) field.get(null);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static int cur = 0;
	private final static int int_offset = cur = 0;
	private final static int longOffset = (cur += 8);
	private final static int byte_offset = (cur += 8);
	private static int SIZE = (cur += 8);

	private long addressOfMemoryBlock;
	private long objectSize = 1024;// 1k
	private long currentAddressInMemoryBlock;

	public void setInt(int value, int indexOfObj) {
		unsafe.putInt(addressOfMemoryBlock + (indexOfObj * objectSize)
				+ int_offset, value);

	}

	public void getInt(int indexOfObj) {
		unsafe.getInt(addressOfMemoryBlock + (indexOfObj * objectSize)
				+ int_offset);

	}

	private long identifyIndex(int indexOfObj) {
		return addressOfMemoryBlock + (indexOfObj * objectSize);
	}

	// public void setLong(final long value) {
	// unsafe.putLong( SIZE, value);
	//
	// }
	//
	// public long getLong() {
	// return unsafe.getLong(pos + long_offset);
	// }

	//
	// public void setByte(byte value) {
	// unsafe.putByte(pos + byte_offset, value);
	// }
	//
	// public int getInt() {
	// return unsafe.getInt(pos + int_offset);
	// }
	//
	//
	// public byte getByte() {
	// return unsafe.getByte(pos + byte_offset);
	// }
	//

	public void navigate(int index) {
		currentAddressInMemoryBlock = identifyIndex(index);
	}

	public static void main(String args[]) {
		long num = 100000L;
		OffHeapObjectList list = new OffHeapObjectList();

		list.allocateMemory(num);// allocate memory for one object

		for (int i = 0; i < num; i++) {
			list.setLong(i, 9999-i);

		}

		for (int i = 0; i < num; i++) {
			System.out.println(" list.getLong(i) " +list.getLong(i));

		}
	}

	private long getLong(int index) {
		return unsafe.getLong(addressOfMemoryBlock + (index * objectSize) + longOffset);
		
	}

	private void allocateMemory(long numberOfObjects) {

		addressOfMemoryBlock = unsafe.allocateMemory(numberOfObjects
				* objectSize);
		System.out.println("result of memory allocation is "
				+ addressOfMemoryBlock);

	}

	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object[] toArray(Object[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean add(Object e) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean containsAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean addAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean addAll(int index, Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean removeAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean retainAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	public void clear() {
		// TODO Auto-generated method stub

	}

	public long get(int indexOfObj) {
		return addressOfMemoryBlock + (indexOfObj * objectSize);

	}

	public void setLong(int index, long longValue) {
		unsafe.putLong(addressOfMemoryBlock + (index * objectSize) + longOffset, longValue);

	}

	public void add(int index, Object element) {
		// TODO Auto-generated method stub

	}

	public Object remove(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public ListIterator listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public ListIterator listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	public List subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}
}