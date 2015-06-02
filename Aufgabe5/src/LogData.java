
public class LogData extends Page{
	
	private boolean isCommit;
	
	public LogData(int pageID, int lsn, int taid, String data, boolean isCommit) {
		super(pageID, lsn, taid, data);
		this.isCommit = isCommit;
	}
	
	public boolean isCommit() {
		return isCommit;
	}
	
}
