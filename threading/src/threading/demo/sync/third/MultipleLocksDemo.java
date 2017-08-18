package threading.demo.sync.third;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MultipleLocksDemo {

	private Random random = new Random();
	
	private Object lock1 = new Object();
	private Object lock2 = new Object();
	
	private List<Integer> list1 = new ArrayList<>();
	private List<Integer> list2 = new ArrayList<>();
	
	/**
	 * Having synchronized methods, two threads can not execute
	 * them at the same time. As a result we can have "time take"
	 * longer. So another way would be synchronize code blocks.
	 * A best practice for that is as follows.
	 */
	/*public synchronized void operationOnList1( ) {
		try {
			Thread.sleep(1);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		list1.add(random.nextInt(100));
	}
	
	public synchronized void operationOnList2() {
		try {
			Thread.sleep(1);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		list2.add(random.nextInt(100));
	}*/
	
	public void operationOnList1( ) {
		
		synchronized (lock1) {
			try {
				Thread.sleep(1);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			list1.add(random.nextInt(100));
		}
	}
	
	public void operationOnList2() {
		
		synchronized (lock2) {
			try {
				Thread.sleep(1);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			list2.add(random.nextInt(100));
		}
	}
	
	public void executeProcess() {
		for (int i = 0; i < 1000; i++) {
			operationOnList1();
			operationOnList2();
		}
	}
	
	public void main() {
		System.out.println("Starting ... ");
		
		long start = System.currentTimeMillis();
		
		/**
		 * With the following line of code, the expected result is
		 * supposed to be like 2 seconds, but it is taking a little
		 * longer. So let's try the following.
		 */
		// executeProcess();
		
		/**
		 * This to work without problems, some methods need to be
		 * synchronized. If not some times it is going to throw
		 * exceptions, or the values are not going to be the 
		 * expected ones.
		 */
		Thread thread1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				executeProcess();
			}
		});
						
		Thread thread2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				executeProcess();
			}
		});
		
		thread1.start();
		thread2.start();
		
		try {
			thread1.join();
			thread2.join();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		long end = System.currentTimeMillis();
		
		System.out.println("Time take: " + (end - start));
		System.out.println("List1: " + list1.size() + "; List2: " + list2.size());
	}
	
	public static void main(String[] args) {
		new MultipleLocksDemo().main();
	}
}
