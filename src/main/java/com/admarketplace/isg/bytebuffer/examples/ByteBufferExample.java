package com.admarketplace.isg.bytebuffer.examples;

import com.admarketplace.isg.util.Util;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ByteBufferExample {
	HashMap<String, String> map = new HashMap<String, String>();
	List<ByteBuffer> allData = new ArrayList<ByteBuffer>();

	public static void main(String args[]) throws InterruptedException {
		Util.printProcessId();
		ByteBufferExample test = new ByteBufferExample();
		test.createHashMap(100000000);

		// Util.echo("array length is " + array.length);
		// byte[] newArray = new byte[array.length];
		// buffer.get(newArray);
		// Util.echo(" char buffer is " + new String(newArray));

	}

	private void createHashMap(long j) throws InterruptedException {
		for (int z = 0; z < j; z++) {
			for (int i = 0; i < 100; i++) {

				String key = "key" + i;
				String value = "value" + i;
				map.put(key, value);

			}
			Util.echo("thread sleeping for 3 seconds");
			
			

			byte[] array = map.toString().getBytes();
			ByteBuffer buffer1 = ByteBuffer.allocate(array.length);
			buffer1.put(array);
			array = null;
			
//			ByteBuffer buffer = ByteBuffer.wrap(array);
			allData.add(buffer1);

		}
	}

	public static String data = "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv"
			+ "asdllsadkflaskdjflaskdlknsdvkljndavkljdnvasdllsadkflaskdjflaskdlknsdvkljndavkljdnv";

}
