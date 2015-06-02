
public class Page {
	private int pageID;
	private int lsn;
	private int taid;
	private String data;
	
	public Page(int pageID, int lsn, int taid, String data) {
		this.pageID = pageID;
		this.lsn = lsn;
		this.data = data;
		this.taid = taid;
	}
	
	public int getPageID() {
		return pageID;
	}
	public void setPageID(int pageID) {
		this.pageID = pageID;
	}
	public int getLsn() {
		return lsn;
	}
	public void setLsn(int lsn) {
		this.lsn = lsn;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public void appendData(String data) {
		this.data += data;
	}

	public int getTaid() {
		return taid;
	}

	public void setTaid(int taid) {
		this.taid = taid;
	}
}
