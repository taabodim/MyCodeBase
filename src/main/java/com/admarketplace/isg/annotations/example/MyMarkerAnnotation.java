package com.admarketplace.isg.annotations.example;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@interface MyMarkerAnnot {
}

public class MyMarkerAnnotation {

	@MyMarkerAnnot
	public void myAnnotationTestMethod() throws NoSuchMethodException, SecurityException {

		Class<? extends MyMarkerAnnotation> cls = this.getClass();
		Method mth = cls.getMethod("myAnnotationTestMethod");
		if (mth.isAnnotationPresent(MyMarkerAnnot.class)) {
			System.out.println("Hey... marker annotation is present.");
		}
	}

	public static void main(String args[]) throws NoSuchMethodException, SecurityException {
		MyMarkerAnnotation mat = new MyMarkerAnnotation();
		mat.myAnnotationTestMethod();
		
	}

}