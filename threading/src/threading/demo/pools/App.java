package threading.demo.pools;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 * ------------------------------------------------------------------
 * https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Executors.html
 *
 * ExecutorService is a subtype of Executor - they are both interfaces.
 * ExecutorService has more functionalities and represents a thread pool.
 *
 * Executors is a utility class allowing us to create ExecutorService
 * objects - also known as thread pools.
 * ------------------------------------------------------------------
 *
 * Question 1 - In this scenario, Creating multiple threads using thread.start()
 * instead of using executors would be like -
 *
 * Thread t1 = new Thread(Processor);
 *
 * Thread t2 = new Thread(Processor);
 *
 * Thread t3 = new Thread(Processor);
 *
 * and making t1.start(),t2.start()and t3.start()
 *
 * Is my understanding correct?
 *
 * Question 2 - While in case of Executors, will it take care of all synchronization
 * between threads internally? like we did explicitly for threads?
 *
 *
 * RESPONSE
 *
 * 1. Not exactly. When using an Executor, the maximum number of threads is determined
 * when you create the service. For instance, this expression from the lecture:
 *
 * Executors.newFixedThreadPool(2)
 *
 * constructs a pool with a limit of two threads. No matter how many tasks you submit,
 * there are always at most two threads running at a time.
 *
 * When you create a thread manually using a Thread constructor, a new threads is generated
 * each time the constructor is called. If you call new Thread(processor) 1000 times, 1000
 * threads will be created.
 *
 * At first glance, this seems like a disadvantage rather than an advantage of thread pools.
 * However, notice that you cannot have more threads than the number of CPU cores running
 * simultaneously. Say, your computer has two cores and the program generates 1000 threads,
 * the underlying OS will schedule those threads in a way that each thread will be executed
 * in an amount of time and wait in a duration about 499 times more than its execution time.
 *
 * From the above example, you can see that having 1000 threads isn't better than having only
 * two. Even worse:
 *
 * - Creating a thread is an expensive operation, thus the creation of 1000 threads will take
 * up a huge amount of resources. On the other hand, when using an executor, threads are
 * created only once and reused over the lifespan of the thread pool. Creating a thread pool
 * with two threads will then be much cheaper than creating 1000 threads.
 *
 * - A thread pool knows how to schedule threads to leverage available resources in the most
 * efficient way. You cannot achieve the same thing with manual thread creation.
 *
 * So, using a thread pool and creating separate threads may look the same regarding the final
 * output, their performance is very different, especially when the number of threads is large.
 *
 * 2. No. You still need to handle the synchronization process yourself.
 *
 * ------------------------------------------------------------------
 * ------------------------------------------------------------------
 * ------------------------------------------------------------------
 * ------------------------------------------------------------------
 * ------------------------------------------------------------------
 * ------------------------------------------------------------------
 * ------------------------------------------------------------------
 *
 *
 **/

class Processor implements Runnable {

    private int id;

    public Processor(int id) {
        this.id = id;
    }

    @Override
    public void run() {

        System.out.println("Starting: " + id);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Completed: " + id);
    }
}

public class App {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 5; i++) {
            executorService.submit(new Processor(i));
        }

        executorService.shutdown();

        System.out.println("All tasks submitted.");

        try {
            executorService.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All tasks completed.");
    }
}

/**
 * Results
 * ======================================================
 *
 * First Test
 * ------------------------------------------------------
 * All tasks submitted.
 * Starting: 0
 * Starting: 1
 * Completed: 0
 * Completed: 1
 * Starting: 2
 * Starting: 3
 * Completed: 3
 * Completed: 2
 * Starting: 4
 * Completed: 4
 * All tasks completed.
 * */
