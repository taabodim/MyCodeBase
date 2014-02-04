package com.admarketplace.isg.annotations.example;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MyAnnotationTest {

	@MySampleAnnotation(name = "myVariable", desc = "testing annotations for vaiable")
	private String nameVariable;
	
	@MySampleAnnotation(name = "test1", desc = "testing annotations")
	public void myTestMethod() {
		// method implementation
	}

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, NoSuchFieldException {

		MyAnnotationTest mat = new MyAnnotationTest();
		mat.myAnnotationTestMethod();
	}

	public void myAnnotationTestMethod() throws NoSuchMethodException, SecurityException, NoSuchFieldException {

		Class<? extends MyAnnotationTest> cls = this.getClass();
		Method mth = cls.getMethod("myTestMethod");
		Field field = cls.getDeclaredField("nameVariable");
		
		MySampleAnnotation myAnnoFromMethod = mth.getAnnotation(MySampleAnnotation.class);
		MySampleAnnotation myAnnoFromField =field.getAnnotation(MySampleAnnotation.class);
		
		System.out.println("myAnnoFromMethod key: " + myAnnoFromMethod.name());
		System.out.println("myAnnoFromMethod value: " + myAnnoFromMethod.desc());
		
		
		System.out.println("myAnnoFromField key: " + myAnnoFromField.name());
		System.out.println("myAnnoFromField value: " + myAnnoFromField.desc());
		

	}
}
