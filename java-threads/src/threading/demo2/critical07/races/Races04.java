package threading.demo2.critical07.races;

/**
 * Race Conditions & Data Races
 *
 * Race Condition
 * - Condition when multiple threads are accesssing a shared resource.
 * - At least one thread is modifying the resource.
 * - The timing of threads'scheduling may cause incorrect results.
 * - The core of the problem is non atomic operations performed on the shared resource.
 *
 * Data Race - Problem
 * - Compiler and CPU may execute the instructions Out Of Order to optimize performance and utilization.
 * - They will do so while maintaining the logical correctness of the code.
 * - Out of Order execution by the compiler the compiler and CPU are important features to speed up the code.
 * - The compiler re-arranges instructions for better
 * --- Branch predication (optimized loops, "if" statements etc.)
 * --- Vectorization - parallel instruction execution (SIMD)
 * --- Prefetching instructions - better cache performance
 * - CPU re-arranges instructions for better hardware units utilization.
 *
 *
 * Data Race - Solutions
 * Establish a happens - before semantics by one of these methods:
 * - Synchronization of methods which modify shared variables
 * - Declaration of shared variables with the volatile keyword
 *
 */
public class Races04 {

    public static void main(String[] args) {
        SharedClass sharedClass = new SharedClass();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                sharedClass.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                sharedClass.checkForDataRace();
            }

        });

        thread1.start();
        thread2.start();
    }

    public static class SharedClass {
        private int x = 0;
        private int y = 0;

        public void increment() {
            x++;
            y++;
        }

        public void checkForDataRace() {
            if (y > x) {
                System.out.println("y > x - Data Race is detected");
            }
        }
    }
}
