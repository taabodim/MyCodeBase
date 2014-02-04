package com.admarketplace.isg.javaassist.example;

import com.admarketplace.isg.util.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/*
 * 
 * Bootstrap ClassLoader
 |
 Extensions ClassLoader
 |
 Trusted Middleware ClassLoader (TMC)
 |
 Shareable Application ClassLoader (SAC)
 |
 Application ClassLoader

 */

/*
 * 
 * 
 * The three main reasons for wanting to create a custom class loader are:

 To allow class loading from alternative repositories.
 This is the most common case, in which an application developer might want to load classes from other locations, for example, over a network connection.

 To partition user code.
 This case is less frequently used by application developers, but widely used in servlet engines.

 To allow the unloading of classes.
 This case is useful if the application creates large numbers of classes that are used for only a finite period. Because a class loader maintains a cache of the classes that it has loaded, these classes cannot be unloaded until the class loader itself has been dereferenced. For this reason, system and extension classes are never unloaded, but application classes can be unloaded when their classloader is.

 */

/*
 * The JVM has three class loaders, each possessing a different scope from which it can load classes. As you descend the hierarchy, the scope of available class repositories widens, and normally the repositories are less trusted:

 Bootstrap
 |
 Extensions
 |
 Application
 */
public class TClassLoader extends ClassLoader {

	public Class findClass(String name) {
		byte[] b;
		try {
			b = loadClassData(name);
			return defineClass(name, b, 0, b.length);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return null;

	}

	
	private byte[] loadClassData(String className) throws IOException {
		String path = getClass().getClassLoader().getResource(".").getPath();
		Util.echo("path is "+path.toString());
		String name =path+ className.replace(".", "/")+".class" ;
		Util.echo("name is "+name.toString());

		File file = new File(name);
		FileInputStream ifs = new FileInputStream(file);

		long length = file.length();

		Util.echo(" file.length is "+length);
		byte[] contents = new byte[(int) length];
		int lengthOfRead = 1024;
		if(length<lengthOfRead) lengthOfRead = (int) length;
		
		int startOfRead = 0;
		while (true) {

			int resultOfRead = ifs.read(contents, startOfRead, (lengthOfRead - startOfRead));
			if (resultOfRead == -1)
				break;
			startOfRead += lengthOfRead;
			if (startOfRead == lengthOfRead)
				break;
		}
		Util.echo(" contents length is "+contents.length);
		return contents;

	}
}
