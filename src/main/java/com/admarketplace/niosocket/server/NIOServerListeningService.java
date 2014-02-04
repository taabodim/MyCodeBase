package com.admarketplace.niosocket.server;

import com.admarketplace.isg.util.Util;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServerListeningService extends Thread {

	private static boolean serverShouldBeRunning = true;
	private ServerSocketChannel serverSocketChannel;
	private Selector selector;

	public NIOServerListeningService() throws IOException {
		selector = Selector.open();
		serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(false);
		//serverSocketChannel.bind(new InetSocketAddress(NIOServer.serverPort));
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(3000);
				Util.echo("NIO Server is waiting for clients to talk.....");
				SocketChannel connectionSocketChannel = serverSocketChannel
						.accept();
				if (connectionSocketChannel != null) {
					connectionSocketChannel.configureBlocking(false);
					connectionSocketChannel.register(selector,
							SelectionKey.OP_READ);

					while (true) {
						selector.select();
						Set<SelectionKey> keys = selector.selectedKeys();
						Iterator<SelectionKey> iter = keys.iterator();
						Util.echo("keys size is ....." + keys.size());
						Thread.sleep(3000);
						while (iter.hasNext()) {

							SelectionKey key = iter.next();
							Util.echo("selectionKey is ....."
									+ key.interestOps());
							if (key.isReadable()) {
								// ready to read
								processRead(key);

							} else if (key.isWritable()) {
								// ready to write

							}
						}

					}

				}
			} catch (Exception e) {

				e.printStackTrace();

			}

		}

	}

	private void processRead(SelectionKey key) {

		ByteBuffer buf = ByteBuffer.allocate(1024);
		try {
			SocketChannel readChannel = (SocketChannel) key.channel();
			readChannel.read(buf);
			Util.echo("buffer contains this : " + new String(buf.array()));
			while(buf.hasRemaining())
			{
				Util.echo("buffer char : "+buf.toString());
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
