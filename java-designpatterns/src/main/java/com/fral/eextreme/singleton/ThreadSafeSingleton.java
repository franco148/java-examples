package com.fral.eextreme.singleton;

/**
 * Thread Safe Singleton: A thread safe singleton in created so that singleton property is maintained even
 * in multithreaded environment. To make a singleton class thread-safe, getInstance() method is made
 * synchronized so that multiple threads can’t access it simultaneously.
 *
 * Pros:
 * 1. Lazy initialization is possible.
 * 2. It is also thread safe.
 *
 * Cons:
 * 1. getInstance() method is synchronized so it causes slow performance as multiple threads can’t access it
 * simultaneously.
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
public class ThreadSafeSingleton {

    // private instance, so that it can be
    // accessed by only by getInstance() method
    private static ThreadSafeSingleton instance;

    private ThreadSafeSingleton() {}

    //synchronized method to control simultaneous access
    synchronized public static ThreadSafeSingleton getInstance() {
        if (instance == null) {
            // if instance is null, initialize
            instance = new ThreadSafeSingleton();
        }

        return instance;
    }
}
