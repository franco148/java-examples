package com.fral.eextreme.singleton;

/**
 * Eager initialization: This is the simplest method of creating a singleton class. In this, object of class is
 * created when it is loaded to the memory by JVM. It is done by assigning the reference an instance directly.
 *
 * It can be used when program will always use instance of this class, or the cost of creating the instance is
 * not too large in terms of resources and time.
 *
 * PROS:
 * 1. Very simple to implement.
 *
 * CONS:
 * 1. May lead to resource wastage. Because instance of class is created always, whether it is required or not.
 * 2. CPU time is also wasted in creation of instance if it is not required.
 * 3. Exception handling is not possible.
 *
 *
 * When to use What
 *  * 1. Eager initialization is easy to implement but it may cause resource and CPU time wastage. Use it only if cost of
 *  * initializing a class is less in terms of resources or your program will always need the instance of class.
 *  * 2. By using Static block in Eager initialization we can provide exception handling and also can control over instance.
 *  * 3. Using synchronized we can create singleton class in multi-threading environment also but it can cause slow
 *  * performance, so we can use Double check locking mechanism.
 *  * 4. Bill Pugh implementation is most widely used approach for singleton classes. Most developers prefer it because
 *  * of its simplicity and advantages.
 *
 * Java code to create singleton class by Eager Initialization
 */
public class EagerInitializationSingleton {

    // public instance initialized when loading the class
    private static final EagerInitializationSingleton instance = new EagerInitializationSingleton();

    private EagerInitializationSingleton() {
        // Private constructor.
    }

    public static EagerInitializationSingleton getInstance() {
        return instance;
    }
}
