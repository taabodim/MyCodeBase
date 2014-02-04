package com.admarketplace.unsafe;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

import com.admarketplace.isg.util.Util;

public class OffHeapPublisher {

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
	private static final long NAME_SIZE = 20;
	private static final long CHAR_SIZE = 8;
	private static long offset = 0;

	private static final long tradeIdOffset = offset += 0;
	private static final long clientIdOffset = offset += 8;
	private static final long venueCodeOffset = offset += 8;
	private static final long instrumentCodeOffset = offset += 8;
	private static final long priceOffset = offset += 8;
	private static final long quantityOffset = offset += 8;
	private static final long sideOffset = offset += 8;
	private static final long tradeEventIdOffset = offset += 8;
	private static final long characterOffset = offset += NAME_SIZE * CHAR_SIZE;
	private static final long nameCharOffset = offset += 8;
	private static final long objectSize = offset += 2;

	private long objectOffset;

	public static long getObjectSize() {
		return objectSize;
	}

	public long getTradeEventId() {
		return unsafe.getLong(objectOffset + tradeEventIdOffset);
	}

	public void setTradeEventId(long id) {
		unsafe.putLong(objectOffset + tradeEventIdOffset, id);
	}

	public void setNameChar(char ch) {
		unsafe.putChar(objectOffset + nameCharOffset, ch);
	}

	public char getNameChar() {
		char ch = unsafe.getChar(objectOffset + nameCharOffset);
		Util.echo("name char is " + ch);
		return ch;
	}

	public char[] getName() {
		char name[] = new char[(int) NAME_SIZE];
		for (int i = 0; i < NAME_SIZE; i++) {
			long charAddr = objectOffset + ((i + 1) * characterOffset);
			char ch = unsafe.getChar(charAddr);
			// Util.echo("read character : "
			// + unsafe.getChar(objectOffset + characterOffset + i)+
			// "offset is "+objectOffset + characterOffset + i);
			Util.echo("read character : ch number is " + ch);
			name[i] = ch;
		}
		return name;
	}

	public void setName(final char[] charArray) {

		for (int i = 0; i < NAME_SIZE; i++) {
			long charAddr = objectOffset + ((i + 1) * characterOffset);
			if (i >= charArray.length) {

//				unsafe.putChar(charAddr, 'Z');
//				Util.echo("written character : ch number is " + (int) 'Z');
			} else {
				unsafe.putChar(charAddr, charArray[i]);
				Util.echo("written character : ch number is "
						+ (int) charArray[i]);
			}

			// Util.echo(" written char is "
			// + unsafe.getChar(objectOffset + characterOffset + i)
			// +" offset is "+objectOffset + characterOffset + i);
		}
	}

	void setObjectOffset(final long objectOffset) {
		this.objectOffset = objectOffset;
	}

	public long getTradeId() {
		return unsafe.getLong(objectOffset + tradeIdOffset);
	}

	public void setTradeId(final long tradeId) {
		unsafe.putLong(objectOffset + tradeIdOffset, tradeId);
	}

	public long getClientId() {
		return unsafe.getLong(objectOffset + clientIdOffset);
	}

	public void setClientId(final long clientId) {
		unsafe.putLong(objectOffset + clientIdOffset, clientId);
	}

	public int getVenueCode() {
		return unsafe.getInt(objectOffset + venueCodeOffset);
	}

	public void setVenueCode(final int venueCode) {
		unsafe.putInt(objectOffset + venueCodeOffset, venueCode);
	}

	public int getInstrumentCode() {
		return unsafe.getInt(objectOffset + instrumentCodeOffset);
	}

	public void setInstrumentCode(final int instrumentCode) {
		unsafe.putInt(objectOffset + instrumentCodeOffset, instrumentCode);
	}

	public long getPrice() {
		return unsafe.getLong(objectOffset + priceOffset);
	}

	public void setPrice(final long price) {
		unsafe.putLong(objectOffset + priceOffset, price);
	}

	public long getQuantity() {
		return unsafe.getLong(objectOffset + quantityOffset);
	}

	public void setQuantity(final long quantity) {
		unsafe.putLong(objectOffset + quantityOffset, quantity);
	}

	public char getSide() {
		return unsafe.getChar(objectOffset + sideOffset);
	}

	public void setSide(final char side) {
		unsafe.putChar(objectOffset + sideOffset, side);
	}
}