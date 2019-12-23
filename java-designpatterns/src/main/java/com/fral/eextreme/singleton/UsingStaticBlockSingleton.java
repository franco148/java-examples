package com.fral.eextreme.singleton;

/**
 * Using static block: This is also a sub part of Eager initialization. The only difference is object is
 * created in a static block so that we can have access on its creation, like exception handling. In this
 * way also, object is created at the time of class loading.
 *
 * It can be used when there is a chance of exceptions in creating object with eager initialization.
 *
 *
 * Pros:
 * 1. Very simple to implement.
 * 2. No need to implement getInstance() method. Instance can be accessed directly.
 * 3. Exceptions can be handled in static block.
 *
 * Cons:
 * 1. May lead to resource wastage. Because instance of class is created always, whether it is required or not.
 * 2. CPU time is also wasted in creation of instance if it is not required.
 *
 * Java code to create singleton class Using Static block
 */
public class UsingStaticBlockSingleton {

    public static UsingStaticBlockSingleton instance;

    private UsingStaticBlockSingleton() {}

    {
        instance = new UsingStaticBlockSingleton();
    }
}
