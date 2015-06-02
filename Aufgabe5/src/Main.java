import java.io.File;


public class Main {

	public static void main(String[] args) {
		
		Thread client1 = new Client(1, false);
		Thread client2 = new Client(2, true);
		// Thread client3 = new Client(3);
		// Thread client4 = new Client(4);
		// Thread client5 = new Client(5);

		client1.start();
		client2.start();
		// client3.start();
		// client4.start();
		// client5.start();
		
		
	}
	
	

}
