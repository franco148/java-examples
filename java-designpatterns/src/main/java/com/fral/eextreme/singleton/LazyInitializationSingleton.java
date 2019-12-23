package com.fral.eextreme.singleton;

/**
 * Lazy initialization: In this method, object is created only if it is needed. This may prevent resource wastage.
 * An implementation of getInstance() method is required which return the instance. There is a null check that if
 * object is not created then create, otherwise return previously created. To make sure that class cannot be
 * instantiated in any other way, constructor is made final. As object is created with in a method, it ensures
 * that object will not be created until and unless it is required. Instance is kept private so that no one can
 * access it directly.
 *
 * It can be used in a single threaded environment because multiple threads can break singleton property because
 * they can access get instance method simultaneously and create multiple objects.
 *
 * Pros:
 * 1. Object is created only if it is needed. It may overcome resource overcome and wastage of CPU time.
 * 2. Exception handling is also possible in method.
 *
 * Cons:
 * 1. Every time a condition of null has to be checked.
 * 2. instance can’t be accessed directly.
 * 3. In multithreaded environment, it may break singleton property.
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
 */
public class LazyInitializationSingleton {

    // private instance, so that it can be
    // accessed by only by getInstance() method
    private static LazyInitializationSingleton instance;

    private LazyInitializationSingleton() {}

    // method to return instance of class
    public static LazyInitializationSingleton getInstance() {
        if (instance == null) {
            // if instance is null, initialize
            instance = new LazyInitializationSingleton();
        }

        return instance;
    }
}
