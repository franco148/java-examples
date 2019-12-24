package threading.demo.locks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Worker {

    private Random random = new Random();

    private Object lock1 = new Object();
    private Object lock2 = new Object();

    private List<Integer> listOne = new ArrayList<>();
    private List<Integer> listTwo = new ArrayList<>();

    /**
     * With the following approach two threads can not run both methods at
     * the same time.
     * */
//    public synchronized void stageOne() {
//        try {
//            Thread.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        listOne.add(random.nextInt(1000));
//    }
//
//    public synchronized void stageTwo() {
//        try {
//            Thread.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        listTwo.add(random.nextInt(1000));
//    }

    /**
     * The following approach is similar to synchronized methods, the difference is that
     * threads can execute the methods at the same time.
     * */
    public void stageOne() {

        synchronized (lock1) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            listOne.add(random.nextInt(1000));
        }
    }

    public synchronized void stageTwo() {

        synchronized (lock2) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            listTwo.add(random.nextInt(1000));
        }
    }

    public void process() {
        for (int i = 0; i < 1000; i++) {
            stageOne();
            stageTwo();
        }
    }

    public void main() {
        System.out.println("Starting...");
        long startTime = System.currentTimeMillis();

        // Scenario 1
        // process();

        // Scenario 2
        // new Thread(this::process).start();

        // Scenario 3
//        Thread thread1 = new Thread(this::process);
//        thread1.start();
//        try {
//            thread1.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        // Scenario 4
        Thread thread1 = new Thread(this::process);
        thread1.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread thread2 = new Thread(this::process);
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        long endTime = System.currentTimeMillis();

        System.out.println("Time taken: " + (endTime - startTime));
        System.out.println("List1 Size: " + listOne.size() + "\nList1 Size: " + listTwo.size());
    }

    /**
     * Initial Result: Scenario 1
     * ===================================
     * Time taken: 3230
     * List1 Size: 1000
     * List1 Size: 1000
     *
     *
     * Initial Result: Scenario 2
     * ===================================
     * Time taken: 43
     * List1 Size: 0
     * List1 Size: 0
     *
     *
     * Initial Result: Scenario 3
     * ===================================
     * Time taken: 3263
     * List1 Size: 1000
     * List1 Size: 1000
     *
     * Initial Result: Scenario 4
     * ===================================
     * Time taken: 5496
     * List1 Size: 2000
     * List1 Size: 2000
     *
     *
     * Initial Result: Scenario 5 - with synchronized methods
     * ===================================
     * Time taken: 6498
     * List1 Size: 2000
     * List1 Size: 2000
     *
     *
     * Initial Result: Scenario 5 - with synchronized blocks
     * ===================================
     * Time taken: 6423
     * List1 Size: 2000
     * List1 Size: 2000
     * */
}
