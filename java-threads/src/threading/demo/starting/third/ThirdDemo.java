package threading.demo.starting.third;

public class ThirdDemo {
	
	public static void main(String[] args) {
		Thread thread1 = new Thread(new Runnable() {
			
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
		});
		
		thread1.start();
	}
}
