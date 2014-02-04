package com.admarketplace.unsafe;

public class WhatsObjectSize {
	private int x;
	private int y;
	private String name = "hassan";

	public static void main(String[] args) {
		System.out.println(ObjectSizeFetcher
				.getObjectSize(new WhatsObjectSize()));
	}
}
