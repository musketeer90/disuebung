import java.util.ArrayList;


public class Itemset {
	public static ArrayList<Itemset> oneItemsets = new ArrayList<Itemset>();
	public static ArrayList<Itemset> twoItemsets = new ArrayList<Itemset>();
	public static ArrayList<ArrayList> Itemsets = new ArrayList<ArrayList>();
	private int support;
	private ArrayList<String> itemset;
	
	public Itemset(ArrayList<String> items) {
		this.itemset = items;
		this.support = -1;
	}
	
	public Itemset(ArrayList<String> items, int support) {
		this.itemset = items;
		this.support = support;
	}
	
	public Itemset(String items, int support) {
		this.itemset = new ArrayList<String>();
		this.itemset.add(items);
		this.support = support;
	}
	
	public String getIdentifier(int index) {
		return itemset.get(index);
	}
	
	public int getSupport() {
		return support;
	}

	public void setSupport(int support) {
		this.support = support;
	}

	public ArrayList<String> getItemset() {
		return itemset;
	}

	public void setItemset(ArrayList<String> items) {
		this.itemset = items;
	}
	
	public void addItem(String item) {
		itemset.add(item);
	}
}
