package com.admarketplace.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

import com.admarketplace.isg.util.Util;

public class TestDirectMemory {
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

//	private static final int NUM_RECORDS = 50 * 1000 * 1000;
	private static final int NUM_RECORDS = 1;
	private static long address;
	private static final OffHeapTrade flyweight = new OffHeapTrade();

	public static void main(final String[] args) {
		for (int i = 0; i < 5; i++) {
			System.gc();
			perfRun(i);
		}
	}

	private static void perfRun(final int runNum) {
		long start = System.currentTimeMillis();

		init();

		System.out.format("Memory %,d total, %,d free\n", Runtime.getRuntime()
				.totalMemory(), Runtime.getRuntime().freeMemory());

		long buyCost = 0;
		long sellCost = 0;

		for (int i = 0; i < NUM_RECORDS; i++) {
			final OffHeapTrade trade = get(i);

			if (trade.getSide() == 'B') {
				buyCost += (trade.getPrice() * trade.getQuantity());
				Util.echo("trade.getTradeEventId() " + trade.getTradeEventId());
				Util.echo("trade.getName() " + trade.getName().toString());
				trade.getNameChar();
			} else {
				sellCost += (trade.getPrice() * trade.getQuantity());
			}
		}

		long duration = System.currentTimeMillis() - start;
		System.out.println(runNum + " - duration " + duration + "ms");
		System.out.println("buyCost = " + buyCost + " sellCost = " + sellCost);

		destroy();
	}

	private static OffHeapTrade get(final int index) {
		final long offset = address
				+ (index * OffHeapTrade.getObjectSize());
		flyweight.setObjectOffset(offset);
		return flyweight;
	}

	public static void init() {
		Util.echo("OffHeapTrade.getObjectSize() : "
				+ OffHeapTrade.getObjectSize());
		final long requiredHeap = NUM_RECORDS
				* OffHeapTrade.getObjectSize();
		Util.echo("requiredHeap is " + requiredHeap);
		address = unsafe.allocateMemory(requiredHeap);

		final byte[] londonStockExchange = { 'X', 'L', 'O', 'N' };
		final int venueCode = pack(londonStockExchange);
		Util.echo("venueCode : " + venueCode);
		final byte[] billiton = { 'B', 'H', 'P' };
		final int instrumentCode = pack(billiton);
		Util.echo("billiton : " + billiton);
		for (int i = 0; i < NUM_RECORDS; i++) {
			OffHeapTrade trade = get(i);

			trade.setTradeId(i);
			trade.setClientId(1);
			trade.setVenueCode(venueCode);
			trade.setInstrumentCode(instrumentCode);

			trade.setPrice(i);
			trade.setQuantity(i);
			trade.setTradeEventId(1000L);
			trade.setName(new char[] { 't', 'a', 'a', 'b' });
			trade.setNameChar('X');
			trade.setSide((i & 1) == 0 ? 'B' : 'S');
		}
	}

	private static void destroy() {
		unsafe.freeMemory(address);
	}

	private static int pack(final byte[] value) {
		int result = 0;
		switch (value.length) {
		case 4:
			result |= (value[3]);
		case 3:
			result |= ((int) value[2] << 8);
		case 2:
			result |= ((int) value[1] << 16);
		case 1:
			result |= ((int) value[0] << 24);
			break;

		default:
			throw new IllegalArgumentException("Invalid array size");
		}

		return result;
	}

	
}