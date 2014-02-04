package com.admarketplace.isg.references.examples;

import com.admarketplace.isg.util.Util;

public 	class Foo {
	private String name = "FooChang";
	private boolean fired=false;

	public Foo(int id) {

		this.name += id;
	}

	public void getFired() {
		fired = true;
		Util.echo(name + " is fired.");
	}

	public void cleanUp() {
		if(!fired) 
			Util.echo(name + "  : I am not fired, back off");
		else
		Util.echo(name + "  is cleaning up his desk before leaving.");
	}
}