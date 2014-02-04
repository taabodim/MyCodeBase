package com.admarketplace.isg.directbuffers.examples;

import java.nio.ByteBuffer;

public class DirectBuffers {

	public static void main(final String[] args) {
		ByteBuffer directBuf = ByteBuffer.allocateDirect(10);
		String str = "I will be outside Heap";
//		dire
//		str.toCharArray();
	}
}
