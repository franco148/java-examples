package threading.demo.starting.first;

/**
 * This is the first way to start with a thread, it needs
 * to extend from Thread class.
 * 
 * @author eXtremeCode
 *
 */
class Runner extends Thread {

	public Runner(String name) {
		super(name);
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(this.getName() + "Hello " + i);
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}						
		}
		
		System.out.println(this.getName() + "Finished!!!...");
	}
}

public class FirstDemo {
	public static void main(String[] args) {
		
		Runner runner1 = new Runner("Thread 1: ");
		/**
		 * After call start() method, it runs in a special thread
		 * but if we just call run() method, it is going to run
		 * in the same thread as main application.
		 */
		runner1.start();
		
		Runner runner2 = new Runner("Thread 2: ");
		runner2.start();
	}
}


