package com.admarketplace.isg.mongo;

//this class tests passing the references
//java is always pass by value
public class TestReferencePassing {

	public static void main(String args[]) {

		TestReferencePassing test = new TestReferencePassing();
	
		A myA = test.new A();
		B myB = test.new B();
		
		myA.setValueOfA(19);
		Integer newA = myA.getValueOfA();
		newA=20;
		
		System.out.println("A is "+myA.getValueOfA());
		System.out.println("newA is "+newA);
		
		System.out.println("B is "+myB.getValueOfB());
	}

	private class A {

		public A() {
		}

		Integer valueOfA;

		public void setValueOfA(Integer val) {
			valueOfA = val;
		}

		public Integer getValueOfA() {
			return valueOfA;
		}
	}

	private class B {

		Integer valueOfB;

		public void setValueOfB(Integer val) {
			valueOfB = val;
		}

		public Integer getValueOfB() {
			return valueOfB;
		}

	}
}
