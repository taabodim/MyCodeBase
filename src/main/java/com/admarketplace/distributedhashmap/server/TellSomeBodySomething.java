package com.admarketplace.distributedhashmap.server;

import com.admarketplace.isg.util.Util;

import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class TellSomeBodySomething {

	private Data data;
	private InetAddress address;
	private Integer port;

	public TellSomeBodySomething(InetAddress address, Integer port,
			final Data data) {
		this.address = address;
		this.port = port;
		this.data = data;

	}

	public void connectSpeakDisconnect() {
		try {
			Socket mySocket = new Socket(address, port);
			ObjectOutputStream out = new ObjectOutputStream(
					mySocket.getOutputStream());

			out.writeObject(data);
			out.flush();
			out.close();
			mySocket.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public void connectKeepSpeakingDisconnect() {
		try {
			Socket mySocket = new Socket(address, port);

			ObjectOutputStream out = new ObjectOutputStream(
					mySocket.getOutputStream());

			for (int i = 0; i < 10; i++) {
				Util.echo("client sending data to server");
				out.writeObject(data);
				out.flush();
				out.reset();
				
			}
			data.setCommand(Constants.ONE_TIME_CONVERSATION);
			out.writeObject(data);
			out.close();
			mySocket.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

}
