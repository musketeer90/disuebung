
public class UserData extends Page{

	private boolean isCommit;
	public UserData(int pageID, int lsn, int taid, String data) {
		super(pageID, lsn, taid, data);
		isCommit = false;
	}
	public boolean isCommit() {
		return isCommit;
	}
	public void setCommit(boolean isCommit) {
		this.isCommit = isCommit;
	}
}
