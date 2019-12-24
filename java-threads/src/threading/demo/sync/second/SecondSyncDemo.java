package threading.demo.sync.second;



public class SecondSyncDemo {
	
	private int count = 0;
	
	public static void main(String[] args) {
		SecondSyncDemo app = new SecondSyncDemo();
		app.doWork();
	}
	
	public void doWork() {
		Thread thread1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					//count ++;
					/*System.out.println(count);*/
					increment();
				}
				System.out.println("Thread1 execution");
			}
		});
		
		Thread thread2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 100; i++) {
					//count ++;
					/*System.out.println(count);*/
					increment();
				}
				System.out.println("Thread2 execution");				
			}
		});
		
		/**
		 * If we just execute like the following, it is not going
		 * to show the expected result, because both threads are updating
		 * the value of the variable almost at the same time. So to
		 * fix that we need to sync the threads.
		 */
		thread1.start();
		thread2.start();
		
		/**
		 * To sync the threads, we need to do the following.
		 * 
		 * Even with the following fix it is supposed to get
		 * 200 as result, but some times it is not going to be
		 * like that, because count++ operation takes some time (3 operations).
		 * 
		 * Even the use of volatile keyword (private volatile int count = 0)
		 * is not going to fix this issue. for that reason,
		 * we need synchronize keyword. Let's update our
		 * code calling a method to increment the count variable.
		 */		
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Count is: " + count);
	}
	
	public synchronized void increment() {
		count++;
	}
}
