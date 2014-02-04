package com.admarketplace.isg.bytecode.manipulation.example;

import com.admarketplace.isg.util.Util;

import java.io.*;

public class ChangeMyByteCode {

	public static void main(String args[]) throws Exception {

		// read a hello.class file
		// modify it to hello World
		// save it
		// load it
		// call it

		ChangeMyByteCode obj = new ChangeMyByteCode();
		obj.runMe();
		
		String foo = "I am a string";
		byte[] bytes = foo.getBytes();
	//	System.out.println( Hex.encodeHexString( bytes ) );
		System.out.println( bytArrayToHex( bytes ) );
//		System.out.println( BaseEncoding.base16().encode(bytes));
	}
	public static String bytArrayToHex(byte[] a) {
		   StringBuilder sb = new StringBuilder();
		   for(byte b: a)
		      sb.append(String.format("%02x", b&0xff));
		   return sb.toString();
		}

	private void runMe() throws Exception {
		File dotClassFile = readAdotClassFile("Hello.class");
		modifyTheDotClassFile(dotClassFile);
		dotClassFile = saveTheFile(dotClassFile);
		Class<?> loadedClass = loadTheClass(dotClassFile);
		callTheMethod(loadedClass);
	}

	private void callTheMethod(Class<?> loadedClass) {
		Util.echo("callTheMethod");
	}

	private Class<?> loadTheClass(File dotClassFile) {

		return null;
	}

	private File saveTheFile(File dotClassFile) {

		return null;
	}

	private void modifyTheDotClassFile(File dotClassFile) {
	}

	private File readAdotClassFile(String filename) throws Exception {
		String fileContent = readByteCode(filename);
		Util.echo("file Content is " + fileContent);
		return null;
	}

	public static String readByteCode(String filename) throws IOException {

		DataInputStream data = new DataInputStream(new FileInputStream(new File(filename)));
		// byte[] byteRead = Util.readFileContents(data);

		// byte byteRead = data.readByte();
		// for(int i=0;i<byteRead.length;i++)
		// {
		// Util.echo("byte read " + byteRead[i]);
		// }

		byte[] byteRead = new byte[1000];
		readFileNBytes(data, 0, 1000, byteRead);
		return null;
	}

	public static int readFileNBytes(InputStream input, int offset, int numberOfBytesToBeRead, byte[] content)
			throws IOException {
		int lastOffset = 0;
		// byte contents[]=new byte[10000], buf[]=new byte[1024];

		InputStream in = new BufferedInputStream(input);
		int bytes_read = 0;

		// for(;;) {
		//read cafe babe
		int tmp = in.read(content);
		
		printArrayContent(content);
		Util.echo("this is read " + bytesToStringUTFNIO(content));
		
//		 tmp = in.read(content, offset, numberOfBytesToBeRead);
//		printArrayContent(content);
//		Util.echo("this is read " + bytesToStringUTFNIO(content));
		// if(tmp == -1)
		// break;
		// System.arraycopy(buf, 0, contents, bytes_read, tmp);
		// bytes_read+=tmp;
		// }

		// byte[] retval=new byte[bytes_read];
		// System.arraycopy(contents, 0, retval, 0, bytes_read);
		lastOffset = offset + numberOfBytesToBeRead;
		return lastOffset;
	}

	//
	// public static String bytesToStringUTFCustom(byte[] bytes) {
	// char[] buffer = new char[bytes.length >> 1];
	// for(int i = 0; i < buffer.length; i++) {
	// int bpos = i << 1;
	// char c = (char)(((bytes[bpos]&0x00FF)<<8) + (bytes[bpos+1]&0x00FF));
	// buffer[i] = c;
	// }
	// return new String(buffer);
	// }

	private static void printArrayContent(byte[] content) {
		for (int i = 0; i < content.length; i++) {
			System.out.print(content[i]+" ");

		}
		Util.echo("\n");
	}

	public static String bytesToStringUTFNIO(byte[] bytes) throws UnsupportedEncodingException {
		String s = new String(bytes, "UTF-8");
		return s;
	}
	
	
	
//	public static String bytesToHex(byte[] bytes) {
//	    char[] hexArray;
//	    char[] hexChars = new char[bytes.length * 2];
//	    int v;
//	    for ( int j = 0; j < bytes.length; j++ ) {
//	        v = bytes[j] & 0xFF;
//	    
//			hexChars[j * 2] = hexArray[v >>> 4];
//	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
//	    }
//	    return new String(hexChars);
//	}
}
