package threading.demo.sync.first;

import java.util.Scanner;

/**
 * Here we are going to use boolean flags with the volatile
 * keyword. Also take a look at why volatile is not good
 * enough for most situations.
 * 
 * @author eXtremeCode
 *
 */
class Processor extends Thread {
	
	/**
	 * You can try like
	 * private boolean running = true;
	 * It may not work as expected, because in this case
	 * the variable is not thread safe. It may not stop the
	 * execution because the thread is not aware about the 
	 * value of running variable.
	 * 
	 * To fix that we are going to use the volatile keyword. With
	 * this implementation we can guarantee that this is going to work.
	 */
	private volatile boolean running = true;
	
	@Override
	public void run() {
		
		while (running) {
			System.out.println("Hello");
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void shutdown( ) {
		running = false;
	}
}

public class FirstSyncDemo {
	
	public static void main(String[] args) {
		
		Processor proc1 = new Processor();
		proc1.start();
		
		/*Processor proc2 = new Processor();
		proc2.start();*/
		
		System.out.println("Press return to stop ...");
		Scanner scanner = new Scanner(System.in);
		String readFromConsole = scanner.nextLine();
		System.out.println(readFromConsole);
		
		proc1.shutdown();
	}
}
