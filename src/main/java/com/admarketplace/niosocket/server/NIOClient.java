package com.admarketplace.niosocket.server;

import com.admarketplace.isg.util.Util;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NIOClient {

	public static void main(String args[]) throws Exception {
		NIOClient client = new NIOClient();
		client.writeDataToServer();

	}

	private void writeDataToServer() throws IOException, InterruptedException {
		String newData = "Halloween is coming to cafe Babe";
		ByteBuffer buf = ByteBuffer.allocate(1024);
		SocketChannel socketChannel = SocketChannel.open();
		socketChannel.connect(new InetSocketAddress(NIOServer.serverPort));

		while (true) {

			Util.echo("writing this to server" + buf.toString());
			buf.put(newData.getBytes());
			while (buf.hasRemaining()) {
				socketChannel.write(buf);

			}
			buf.clear();
			Thread.sleep(10000);
		}
	}

}
