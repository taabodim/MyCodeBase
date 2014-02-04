package com.admarketplace.isg.enumsingleton.example;

import com.admarketplace.isg.util.Util;

//this class demonstrates how to have a thread-safe singleton using enum.

public enum SingletonEnumWay {

	INSTANCE;

	private DataSource _dataSource;
	private JdbcTemplate _jdbcTemplate;

	private SingletonEnumWay() {

	}

	public void dostuff() {
		Util.echo("Singleton is doing stuff");
	}

	class DataSource {
	}

	class JdbcTemplate {
	}

	public static void main(String args[]) {
		// use it as ...
		SingletonEnumWay.INSTANCE.dostuff();

	}
}