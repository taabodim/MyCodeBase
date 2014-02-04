package com.admarketplace.isg.annotations.example;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MySampleAnnotation {

	String name();

	String desc();
}