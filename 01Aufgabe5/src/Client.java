
public class Client extends Thread{
	private int number;
	private boolean error;
	
	public Client(int number, boolean error) {
		this.number = number;
		this.error = error;
	}
	
	//begin transaction and write up to 5 times onto a page according to its thread number
	public void run() {
		while(true) {
		int numberOfWrites = (int)((Math.random()*6)+1);
		
		int taid = PersistenceManager.beginTransaction();
		System.out.println("Begin of Transaction with TAID: " + taid);
		
		for(int i = 0; i < numberOfWrites; i++) {
			int pageid; 
			if(error)
				pageid = number*10;
			else
				pageid = (int)(Math.random()*9 + number*10);
			String data = "";
			for(int a = 0; a < 10; a++)
			{
				data += (char)(Math.random()*26 + 65);
			}
			PersistenceManager.write(taid, pageid, data);
			System.out.println(taid + ": Write \"" + data + "\" to page " + pageid);
					
			try{
				Thread.sleep(1000);
			}catch(InterruptedException e){
				return;
			}
		}
		try {
			PersistenceManager.commit(taid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(taid + ": COMMIT");
		}
	}
}
