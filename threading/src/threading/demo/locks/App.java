package threading.demo.locks;

/**
 * Multiple LOCKS; using SYNCHRONIZED code blocks
 *
 * --------------------------------------------------------------------------------------------------------
 * The synchronized keyword prevents a block of code or method from being executed by more than one thread.
 * The larger a protected area is, the longer a blocked thread must wait. The reason is simple: the other
 * thread that is holding the lock needs more time to execute the synchronized code.
 *
 * A method is often bigger than a code block, hence a synchronized method is slower than a block.
 * --------------------------------------------------------------------------------------------------------
 * I think this is your key question: "How does thread2 gets to know that thread1 has already acquired the
 * lock on lock1 object and then it switches to lock2?"
 *
 * In fact, that's the purpose of the lock and a feature of the Java programming language itself. How it's
 * implemented is at a too low level and different across JVM implementations, hence not what we need to
 * care about.
 *
 * The important point is the JVM blocks a thread if it's trying to acquire a lock that's being held by
 * another thread.
 * --------------------------------------------------------------------------------------------------------
 * Actually in this small code fragment, it doesn't affect when you lock on the List object itself. However,
 * doing so is a bad practice.
 *
 * The problem is that you may want to reuse List objects in multiple ways. In a small program, it's easy
 * to keep track; but in a very large one, it's very difficult to figure out the issue if something goes
 * wrong with your code.
 *
 * Using different variables for different purposes makes your code easier to read and maintain. This is not
 * only true with monitor locks, but also with all kinds of variables.
 * --------------------------------------------------------------------------------------------------------
 * A single process takes 2 seconds to run, while 2 processes in this lecture also take 2 seconds. This means
 * that you have done twice the workload within the same amount of time. If you don't use multi-threading, 2
 * processes will need 4 seconds to complete.
 *
 * However, speeding up the whole process is just a small part of the picture. A personal computer often has
 * only 2 cores (note that if a CPU is advertised to have 4 cores, it's very likely that it has only 2 physical
 * cores - number 4 over here just refers to the number of logical cores). Therefore, if you increase the
 * number of threads to more than 2, you won't see much of a difference.
 *
 * The key point of multi-threading is to allow you to dispatch a long-running execution process to the
 * background, preventing it from stalling the whole application. For instance, if you want to read some
 * data from a database over the internet, you may need to wait for a couple of seconds before doing anything
 * else. Without multi-threading, all you can do after sending the request is to sit down and wait until the
 * data gets back. Multi-threading enables you to use another thread to handle the data when it reaches your
 * computer. The main thread, which sent the request before, can keep going with other things.
 * --------------------------------------------------------------------------------------------------------
 * Calling join is just a way to merge results from different threads. Notice that join is only called after
 * all the other threads have been spawned.
 *
 * If you use only one child thread, using join will defeat the purpose of multi-threading as you said. However,
 * with two or more child threads, only one thread is paused - the main thread, while more than one is
 * running - all the child threads. Thus you still gain performance.
 *
 * Notice that if you want to see the results of child threads, you always need to call a method similar
 * to join. For example, if you use a executor service, you can call the method:
 *
 * Future<?> future = executorService.submit(Runnable task);
 *
 * The result of the submitted task in this case can be retrieved using the method:
 *
 * Object result = future.get();
 *
 * The thread calling future.get method must wait until the child thread is complete, just like thread.join
 * does.
 * --------------------------------------------------------------------------------------------------------
 * The join methods force the main thread to wait until both threads t1 and t2 complete their job. If you
 * comment out those invocations, the outcome will be prematurely printed when t1 and t2 aren't done.
 * That's why you get inconsistent results.
 * --------------------------------------------------------------------------------------------------------
 * Join and synchronized are entirely different concepts.
 *
 * Let's take a look at join first. Assuming a thread, called threadA, is executing and encounters an
 * invocation of the join method: threadB.join(). Normally, the two threads, threadA and threadB, run
 * independently and don't know about each other. When threadA faces that method, however, it will wait
 * until threadB finishes, and only proceed thereafter. This makes sure threadA always sees the effects
 * caused by threadB.
 *
 * The synchronized keyword, on the other hand, works on a method or a block of code. This keyword ensures
 * that the associated method or block is executed by only one thread at a time. Which thread runs first
 * is determined by the JVM and out of our control. When a thread is running, other threads must wait until
 * the first thread is done with the synchronized method/block.
 * --------------------------------------------------------------------------------------------------------
 * --------------------------------------------------------------------------------------------------------
 * */
public class App {

    public static void main(String[] args) {
        new Worker().main();
    }
}
