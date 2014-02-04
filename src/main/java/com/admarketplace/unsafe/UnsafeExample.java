//package com.admarketplace.unsafe;
//
//import java.lang.reflect.Field;
//
//import java.io.Externalizable;
//import java.lang.reflect.Field;
//import java.util.HashMap;
//import java.util.Map;
//
//import sun.misc.Unsafe;
//
//public class UnsafeExample {
//	
//
//	
//	public class UnsafeCache<K extends Externalizable, V extends Externalizable> {
//	    private final int recordSize;
//	    private final Unsafe backingMap;
//	    private final Map<K, Integer> keyToOffset;
//	    private long address;
//	    private int capacity;
//	    private int currentOffset;
//
//	    public UnsafeCache(int recordSize, int maxRecords) {
//	        this.recordSize = recordSize;
//	        this.backingMap = getUnsafeBackingMap();
//	        this.capacity = recordSize * maxRecords;
//	        this.address = backingMap.allocateMemory(capacity);
//	        this.keyToOffset = new HashMap<K, Integer>();
//	    }
//
//	    public void put(K key, V value) {
//	        if(currentOffset + recordSize < capacity) {
//	            store(currentOffset, value);
//	            keyToOffset.put(key, currentOffset);
//	            currentOffset += recordSize;
//	        }
//	    }
//
//	    public V get(K key) {
//	        int offset = keyToOffset.get(key);
//	        if(offset >= 0)
//	            return retrieve(offset);
//			return null;
//
////	        throw new KeyNotFoundException();
//	    }
//
////	    public V retrieve(int offset) {
////	        byte[] record = new byte[recordSize];
////
////	        //Inefficient
////	        for(int i=0; i<record.length; i++) {
////	            record[i] = backingMap.getByte(address + offset + i);
////	        }
////
////	        //implementation left as an exercise
//////	        return internalize(record);
////	    }
////
////	    public void store(int offset, V value) {
////	        byte[] record = externalize(value);
////
////	        //Inefficient
////	        for(int i=0; i<record.length; i++) {
////	            backingMap.putByte(address + offset + i, record[i]);
////	        }
////	    }
//
//	    private Unsafe getUnsafeBackingMap() {
//	        try {
//	            Field f = Unsafe.class.getDeclaredField("theUnsafe");
//	            f.setAccessible(true);
//	            return (Unsafe) f.get(null);
//	        } catch (Exception e) { }
//	        return null;
//	    }
//	}
//}
