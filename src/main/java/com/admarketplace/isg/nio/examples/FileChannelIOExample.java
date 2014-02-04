package com.admarketplace.isg.nio.examples;

import com.admarketplace.isg.util.Util;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelIOExample {
	public static void main(String args[]) throws Exception {
		
		RandomAccessFile myFile = new RandomAccessFile("Hello.class", "rw");
		FileChannel inChannel = myFile.getChannel();

		ByteBuffer buf = ByteBuffer.allocate(1024 * 1024);

		int bytesRead = inChannel.read(buf);
		while (bytesRead != -1) {

			Util.echo("Read " + bytesRead);
			buf.flip();

			while (buf.hasRemaining()) {
				Util.echo(Integer.toHexString(buf.get())+"");
			}

			buf.clear();
			bytesRead = inChannel.read(buf);
		}
		myFile.close();
	}
}
