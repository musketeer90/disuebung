import java.util.ArrayList;
import java.util.ListIterator;


public class PersistenceManager {
	static final private PersistenceManager pm;
	
	private ArrayList<UserData> userData = new ArrayList<UserData>();
	private ArrayList<LogData> logData = new ArrayList<LogData>();
	private SynchronizedCounter lsn = new SynchronizedCounter();
	private SynchronizedCounter taid = new SynchronizedCounter();
	
	static {
		try {
			pm = new PersistenceManager();
			pm.taid.increment();
		} catch(Throwable e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	private PersistenceManager() {}
	
	static public PersistenceManager getInstance() {
		return pm;
	}
	
	static public synchronized int beginTransaction() {
		int id = pm.taid.value();
		pm.taid.increment();
		
		return id;
	}
	
	static public synchronized void write(int taid, int pageID, String data) {	
		UserData ud = new UserData(pageID, pm.lsn.value(), taid, data);
		pm.userData.add(ud);
		LogData ld = new LogData(pageID, pm.lsn.value(), taid, data, false);
		pm.logData.add(ld);
		pm.lsn.increment();
		Buffer.writeLD(ld);
		Buffer.writeUD(ud.getPageID(), ud);
	}
	
	static public synchronized void commit(int taid) {
		ListIterator<UserData> iteratorUser = pm.userData.listIterator();
		UserData ud = null;
		while(iteratorUser.hasNext()) {
			ud = iteratorUser.next();
			if(ud.getTaid() == taid) {
				ud.setCommit(true);
			}
		}
//		Buffer.flushBuffer();
	}
}
