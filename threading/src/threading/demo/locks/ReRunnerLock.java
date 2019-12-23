package threading.demo.locks;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReRunnerLock {

    private int count = 0;

    // This means that once a thread has acquired this lock
    // and once thread is locked this lock it. Can lock it again
    // if it wants to earn the lot just keeps count............
    private Lock lock = new ReentrantLock();
    private Condition cond = lock.newCondition();

    private void increment() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

    public void firstThread() throws InterruptedException {
        // This way is not good, because once the increment method throws
        // an exception, what will happen is that you will never call unlock.
//        lock.lock();
//        increment();
//        lock.unlock();

        lock.lock();

        System.out.println("Waiting...");
        cond.await();
        System.out.println("Woken up!");

        try {
            increment();
        } finally {
            lock.unlock();
        }
    }

    public void secondThread() throws InterruptedException {

        Thread.sleep(1000);
        lock.lock();

        System.out.println("Press the return key!");
        new Scanner(System.in).nextLine();
        System.out.println("Got return key!");

        cond.signal();

        try {
            increment();
        } finally {
            lock.unlock();
        }
    }

    public void finished() {
        System.out.println("Count is: " + count);
    }
}
