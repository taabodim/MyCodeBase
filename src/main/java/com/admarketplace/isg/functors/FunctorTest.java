package com.admarketplace.isg.functors;//package com.admarketplace.isg.functors;
//
//import java.util.Arrays;
//import java.util.List;
//
//import org.apache.commons.functor.UnaryFunction;
//import org.apache.commons.functor.UnaryPredicate;
//import org.apache.commons.functor.UnaryProcedure;
//
//public class FunctorTest {
//
//	private static UnaryProcedure print = new UnaryProcedure() {
//
//		@Override
//		public void run(Object obj) {
//			System.out.print(obj.toString() + " ");
//		}
//
//	};
//	
//	private static UnaryFunction doubler = new UnaryFunction() {
//		@Override
//		public Object evaluate(Object arg0) {
//
//			return Double.parseDouble(arg0.toString()) * 2;
//		}
//	};
//
//	
//	private static UnaryPredicate isEven = new UnaryPredicate() {
//		@Override
//		public boolean test(Object obj) {
//			return Integer.parseInt(obj.toString()) % 2 == 0;
//		}
//
//	};
//	
//	public static void main(String args[]) throws Exception {
//		predicateExample();
//		unaryFunctionExample();
//		procedureExample();
//        codeReuseExampleUsingFunctors();
//	}
//
//	private static void codeReuseExampleUsingFunctors() {
//		List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
//		for( Integer number : numbers ) {
//		    if(isEven.test(number)) {
//				print.run(doubler.evaluate(number));
//		    }
//		}
//	}
//
//	private static void procedureExample() {
//		List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
//
//		for (Integer number : numbers) {
//			print.run(number);
//		}
//
//	}
//
//	private static void unaryFunctionExample() {
//		List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
//
//		
//
//		for (Integer number : numbers) {
//			Integer value = (Integer) doubler.evaluate(number);
//			System.out.print(value + " ");
//		}
//
//	}
//
//	private static void predicateExample() {
//
//		List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
//
//		
//
//		for (Integer number : numbers) {
//			if (isEven.test(number)) {
//				System.out.print(number + ' ');
//			}
//
//		}
//	}
//}
