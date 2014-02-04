package com.admarketplace.isg.enums.example;
import java.util.LinkedHashMap;
import java.util.Map;

public class ForceInitializeEnum {

	 public static void main(String... args) { 
		    System.out.println(EnumTest.map); 
		  } 
	 
	 enum EnumTest { 
		  FOO, BAR, BAZ; 

		  private static final Map<String,EnumTest> map = new LinkedHashMap<String,EnumTest>(); 
		  static { 
		      for(EnumTest e : EnumTest.values())
		        map.put(e.name(), e); 
		  } 

		  public static void main(String... args) { 
		    System.out.println(EnumTest.map); 
		  } 
		}
}


