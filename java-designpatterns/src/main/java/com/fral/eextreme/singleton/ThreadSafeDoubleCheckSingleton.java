package com.fral.eextreme.singleton;

/**
 * Lazy initialization with Double check locking: In this mechanism, we overcome the overhead problem of
 * synchronized code. In this method, getInstance is not synchronized but the block which creates instance
 * is synchronized so that minimum number of threads have to wait and that’s only for first time.
 *
 * Pros:
 * 1. Lazy initialization is possible.
 * 2. It is also thread safe.
 * 3. Performance overhead gets reduced because of synchronized keyword.
 *
 * Cons:
 * 1. First time, it can affect performance.
 *
 *
 * When to use What
 * 1. Eager initialization is easy to implement but it may cause resource and CPU time wastage. Use it only if cost of
 * initializing a class is less in terms of resources or your program will always need the instance of class.
 * 2. By using Static block in Eager initialization we can provide exception handling and also can control over instance.
 * 3. Using synchronized we can create singleton class in multi-threading environment also but it can cause slow
 * performance, so we can use Double check locking mechanism.
 * 4. Bill Pugh implementation is most widely used approach for singleton classes. Most developers prefer it because
 * of its simplicity and advantages.
 */
public class ThreadSafeDoubleCheckSingleton {

    // private instance, so that it can be
    // accessed by only by getInstance() method
    private static ThreadSafeDoubleCheckSingleton instance;

    private ThreadSafeDoubleCheckSingleton() {

    }

    public static ThreadSafeDoubleCheckSingleton getInstance() {
        if (instance == null) {
            //synchronized block to remove overhead
            synchronized (ThreadSafeDoubleCheckSingleton.class) {
                if (instance == null) {
                    // if instance is null, initialize
                    instance = new ThreadSafeDoubleCheckSingleton();
                }
            }
        }
        return instance;
    }
}
