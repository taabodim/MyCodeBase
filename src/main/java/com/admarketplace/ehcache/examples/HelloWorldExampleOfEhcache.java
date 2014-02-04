package com.admarketplace.ehcache.examples;

import com.admarketplace.isg.util.Util;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class HelloWorldExampleOfEhcache {

	public static void main(final String[] args) {
		// run it with this arg
		// -Dcom.tc.productkey.path=/Users/mahmoudtaabodi/Documents/tmp/terracotta-license.key

		// Create a cache manager
		final CacheManager cacheManager = new CacheManager(
				"src/main/resources/ehcache.xml");
		// Util.echo("active config "
		// +cacheManager.getActiveConfigurationText());
		// create the data store called "hello-world"
		String[] cacheNamesForManager = cacheManager.getCacheNames();
		for (int i = 0; i < cacheNamesForManager.length; i++) {
			Util.echo("cache names are : " + cacheNamesForManager[i]);
		}
		final Cache dataStore = cacheManager.getCache("hello-world");

		// create a key to map the data to
		final String key = "greeting";

		// Create a data element
		final Element putGreeting = new Element(key, "Hello, World!");

		// Put the element into the data store
		dataStore.put(putGreeting);

		// Retrieve the data element
		final Element getGreeting = dataStore.get(key);

		// Print the value
		System.out.println(getGreeting.getObjectValue());
	}
}