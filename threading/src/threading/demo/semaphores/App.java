package threading.demo.semaphores;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {

    public static void main(String[] args) throws Exception {

//        Semaphore semaphore = new Semaphore(1);
//
//        semaphore.release();
//        semaphore.acquire();
//
//        System.out.println("Available permits: " + semaphore.availablePermits());

//        Connection.getInstance().connect();

        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 200; i++) {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    Connection.getInstance().connect();
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.DAYS);
    }
}
