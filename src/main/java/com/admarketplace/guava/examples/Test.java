package com.admarketplace.guava.examples;

import com.admarketplace.isg.util.Util;
import com.google.common.base.Objects;

public class Test {

	public static void main(String args[]) {
		Util.printProcessId();
		// Objects.toStringHelper(self)
		Test test = new Test();
		Util.echo(test.toStringTest(new Car("benz", "m_taabodi", "khar")));
	}

	public String toStringTest(Car car) {

		// return Objects.toStringHelper(this).add("name", null).add("id",
		// car.userId).add("pet", car.petName) // petName
		// .omitNullValues().toString();

		return Objects.toStringHelper(this).add("name", car.name).add("id", car.userId).add("pet", car.petName) // petName
				.omitNullValues().toString();
	}
}

class Car {
	public String userId;
	public String petName;
	public String name;

	public Car(String name, String userId, String petName) {
		this.name = name;
		this.userId = userId;
		this.petName = petName;

	}

}
