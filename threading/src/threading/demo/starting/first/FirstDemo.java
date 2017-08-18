package threading.demo.starting.first;

/**
 * This is the first way to start with a thread, it needs
 * to extend from Thread class.
 * 
 * @author eXtremeCode
 *
 */
class Runner extends Thread {
	
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("Hello " + i);
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}						
		}
		
		System.out.println("Finished!!!...");
	}
}

public class FirstDemo {
	public static void main(String[] args) {
		
		Runner runner1 = new Runner();
		/**
		 * After call start() method, it runs in a special thread
		 * but if we just call run() method, it is going to run
		 * in the same thread as main application.
		 */
		runner1.start();
		
		Runner runner2 = new Runner();
		runner2.start();
	}
}


