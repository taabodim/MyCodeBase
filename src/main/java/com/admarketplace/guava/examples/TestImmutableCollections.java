package com.admarketplace.guava.examples;

import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimap;

import java.util.Set;
import java.util.WeakHashMap;

public class TestImmutableCollections {
	public static final ImmutableSet<String> COLOR_NAMES = ImmutableSet.of("red", "orange", "yellow", "green", "blue",
			"purple");
	public static final ImmutableSet<String> GOOGLE_COLORS = ImmutableSet.<String> builder().addAll(COLOR_NAMES)
			.add("blackPurpleGreen").build();

	static final Multimap<String, Integer> STRING_TO_INTEGER_MULTIMAP = new ImmutableListMultimap.Builder<String, Integer>()
			.put("one", 1).putAll("several", 1, 2, 3).putAll("many", 1, 2, 3, 4, 5).build();

	static final ImmutableMap<String, Integer> WORD_TO_INT = new ImmutableMap.Builder<String, Integer>().put("one", 1)
			.put("two", 2).put("three", 3).build();

	static final WeakHashMap weakMap  = new WeakHashMap(100);
	
	class Foo {
		Set<Bar> bars;

		Foo(Set<Bar> bars) {
			this.bars = ImmutableSet.copyOf(bars); // defensive copy!
		}
	}

	class Bar {
	}
}
