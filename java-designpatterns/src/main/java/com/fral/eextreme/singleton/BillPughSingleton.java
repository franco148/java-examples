package com.fral.eextreme.singleton;

/**
 * Bill Pugh Singleton Implementation: Prior to Java5, memory model had a lot of issues and above methods caused
 * failure in certain scenarios in multithreaded environment. So, Bill Pugh suggested a concept of inner static
 * classes to use for singleton.
 *
 * When the singleton class is loaded, inner class is not loaded and hence doesn’t create object when loading the
 * class. Inner class is created only when getInstance() method is called. So it may seem like eager initialization
 * but it is lazy initialization.
 * This is the most widely used approach as it doesn’t use synchronization.
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
public class BillPughSingleton {

    private BillPughSingleton() {
    }

    private static class BillPughInstanceClass {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    public static BillPughSingleton getInstance() {
        return BillPughInstanceClass.INSTANCE;
    }
}
