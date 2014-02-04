package com.admarketplace.isg.enumsingleton.example;

import java.io.Serializable;

public class SingletonExamplePreJava5 {

	
	
}
/*
 * Let's go over the code. First, you want the class to be final.
 *  In this case, I've used the final keyword to let the users know it is final. 
 *  Then you need to make the constructor private to prevent users to create their own Foo. 
 *  Throwing an exception from the constructor prevents users to use reflection to create a second Foo.
 *   Then you create a private static final Foo field to hold the only instance, and a public static Foo getInstance()
 *    method to return it. The Java specification makes sure that the constructor is only called when the class is first used.
 */
class Foo {

    private static final Foo INSTANCE = new Foo();

    private Foo() {
        if (INSTANCE != null) {
            throw new IllegalStateException("Already instantiated");
        }
    }

    public static Foo getInstance() {
		return INSTANCE;
    }
}






/*
 * Since the line private static final Foo1 INSTANCE = new Foo1(); 
 * is only executed when the class FooLoader is actually used, this takes care of the lazy instantiation, 
 * and is it guaranteed to be thread safe.

When you also want to be able to serialize your object you need to make sure that deserialization won't create a copy.
 * The method readResolve() will make sure the only instance will be returned, even when the object
 *  was serialized in a previous run of your program.
 */
final class Foo1 implements Serializable {

    private static final long serialVersionUID = 1L;

    private static class FooLoader {
        private static final Foo1 INSTANCE = new Foo1();
    }

    private Foo1() {
        if (FooLoader.INSTANCE != null) {
            throw new IllegalStateException("Already instantiated");
        }
    }

    public static Foo1 getInstance() {
        return FooLoader.INSTANCE;
    }

    @SuppressWarnings("unused")
    private Foo1 readResolve() {
        return FooLoader.INSTANCE;
    }
}