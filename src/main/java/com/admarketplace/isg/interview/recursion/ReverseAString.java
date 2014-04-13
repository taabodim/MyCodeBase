package com.admarketplace.isg.interview.recursion;

import com.admarketplace.isg.util.Util;

import java.util.TreeMap;

public class ReverseAString {
	public static void main(String args[]) throws Exception {
		String str = "taabodi";
		String newStr = "" ;
		TreeMap f;
//		ConcurrentHashMap<K, V>
//		ConcurrentLinkedQueue<E>
		for (int i=0;i<str.length();i++)
		{
			char current = str.charAt(str.length()-1-i);
			newStr+=current;
			Util.echo("current is "+newStr);
		}

		
		Util.echo("swap : "+fetchLastCharAndAppend("taabodi bahoosh ast"));
	}

	private static String fetchLastCharAndAppend(String myStr) {
		if(myStr.length()>0)
		{
			char lastCharacter = myStr.charAt(myStr.length()-1);
			String newStringMinusLastChar = myStr.substring(0,myStr.length()-1);
			return lastCharacter+fetchLastCharAndAppend(newStringMinusLastChar);
		}
		else return "";
	}
}
