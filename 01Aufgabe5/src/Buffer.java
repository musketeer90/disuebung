import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;


public class Buffer {
	static Hashtable<Integer, UserData> buffer = new Hashtable<Integer, UserData>();
	
	public static synchronized void writeUD(int pageID, UserData ud) {
		buffer.put(pageID, ud);
		
		if(buffer.size() >= 5) {
			flushBuffer();
		}
	}
	
	public static synchronized void writeLD(LogData ld) {
		FileWrite.writeOutput("LD_taid_"+ld.getTaid()+"_pageID_"+ld.getPageID()+"_lsn_"+ld.getLsn()+".txt", ld.getData());
	}
	
	public static synchronized void flushBuffer() {
		//store committed transactions persistently and remove from buffer
		Set<Integer> keys = buffer.keySet();
		ArrayList<Integer> delete = new ArrayList<Integer>();
		for(int key:keys) {
			if(buffer.get(key).isCommit()) {
				FileWrite.writeOutput("UD_taid_"+buffer.get(key).getTaid()+"_pageID_"+key+"_lsn_"+buffer.get(key).getLsn()+".txt", buffer.get(key).getData());
				delete.add(key);
			}
		}
		//remove committed transactions from buffer
		for(int i = 0; i < delete.size(); i++)
			buffer.remove(delete.get(i));
	}
}
