package com.admarketplace.isg.bytecode.manipulation.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadBytesFromFile {
	public static void main(String[] args) throws IOException {
		// if(args.length == 0)
		// {
		// System.out.println("\nUsage: java ReadFileBytes <fileToBeRead>");
		// System.exit(1);
		// }
		File file = new File("Hello.class");
		FileInputStream fin = new FileInputStream(file);
		int readByte = fin.read();
		while (readByte != -1) {
			System.out.print(Integer.toHexString(readByte) + " ");
//			System.out.print( + " ");
			readByte = fin.read();
			System.out.println();
		}
		
	}
}
