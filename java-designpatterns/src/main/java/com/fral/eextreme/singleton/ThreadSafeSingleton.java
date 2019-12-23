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
 * 1. getInstance() method is synchronized so it causes slow performance as multiple threads can’t access it simultaneously.
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
