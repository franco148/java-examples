package threading.demo.starting.second;

/**
 * This second approach is going to be implementing the Runnable
 * interface.
 * 
 * @author eXtremeCode
 *
 */
class Runner implements Runnable {

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

public class SecondDemo {
	
	public static void main(String[] args) {
		Thread thread1 = new Thread(new Runner());
		Thread thread2 = new Thread(new Runner());
		
		thread1.start();
		thread2.start();
	}

}
