package com.admarketplace.isg.javaassist.example;

import com.admarketplace.isg.util.Util;

import java.lang.reflect.Method;

public class TClassLoaderTest {
	TClassLoader myClassLoader = new TClassLoader();

	public static void main(String args[]) throws Exception {
		TClassLoaderTest loaderTest = new TClassLoaderTest();
		loaderTest.LoadClassDynamically();

	}

	private void LoadClassDynamically() throws Exception {

		String className = LoadMePlease.class.getName();

		Class testClass = myClassLoader.findClass(className);
		// you have to use reflection api to use this object! you cant cast it!!
		Object testObj = testClass.newInstance();
		Method[] methods = testClass.getMethods();
		Method fMethod = testClass.getMethod("f", int.class);
		Util.echo("testObj.f(2) is " + fMethod.invoke(testObj, 2));
	}
}
