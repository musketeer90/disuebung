import java.util.ArrayList;
import java.util.HashMap;


public class Apriori {
	
	//array containing all transactions
	private String[] transaction;
	//list containing the uniqe identifiers of elements in transactions
	private ArrayList<String> identifier;
	
	public Apriori(String[] transactions) {
		this.transaction = transactions;
		identifier = new ArrayList<String>();
		identifier = findDifferentIdentifiers();
	}
	
	//find the different, unique itemset identifiers
	public ArrayList<String> findDifferentIdentifiers() {
		//go through each transaction
		for(String str:transaction) {
			//get the contents of each transaction, check if they were already registered as identifier
			//if not then add them to the list of identifiers
			String[] id = str.split(" ");
			for(String str2:id) {
				if(!identifier.contains(str2))
					identifier.add(str2);
			}
		}
		return identifier;
	}
	
	public HashMap<String, Integer> find_frequent_1_itemsets() {
		//hash map storing each identifier and its frequency
		HashMap<String, Integer> frequency = new HashMap<String, Integer>();
		//initialize hash map with each identifier and count 0
		for(int i = 0; i < identifier.size(); i++)
			frequency.put(identifier.get(i), 0);
		//add 1 to identifier count whenever the identifier occurs in a transaction
		ArrayList<String> doubleEntries = new ArrayList<String>();
		for(String str:transaction) {
			String[] id = str.split(" ");
			for(String str2:id) {
				//ignore double entries in a transaction
				if(doubleEntries.contains(str2))
					continue;
				doubleEntries.add(str2);
				//update count
				int temp = frequency.get(str2);
				frequency.replace(str2, temp, temp+1);
			}
			doubleEntries.clear();
		}
		System.out.println("#identifiers: " + frequency.size());
		return frequency;
	}
	
	public ArrayList<Itemset> create_1_itemsets(double support) {
		HashMap<String, Integer> frequency = find_frequent_1_itemsets();
		//for each identifier create an itemset containing only this item
		//with support = number of occurences of this identifier
		for(String str:frequency.keySet()) {
			//if the itemset's support is big enough create a new itemset containing
			//only this one item
			if(frequency.get(str) >= support)
				Itemset.oneItemsets.add(new Itemset(str, frequency.get(str)));
		}
		System.out.println("# 1 itemsets: " + Itemset.oneItemsets.size());
		Itemset.Itemsets.add(Itemset.oneItemsets);
		return Itemset.oneItemsets;
	}
	
	public void find_frequen_2_itemsets(double support) {
		HashMap<Itemset, Integer> hm = new HashMap<Itemset, Integer> ();
		//create itemsets of size k
		ArrayList<Itemset> candidateItemset = new ArrayList<Itemset>();
		//for k == 2 just add together all 1 itemsets
		for(int i = 0; i < Itemset.oneItemsets.size(); i++) {
			for(int j = i+1; j < Itemset.oneItemsets.size(); j++) {
				ArrayList<String> temp = new ArrayList<String>();
				temp.add(Itemset.oneItemsets.get(i).getIdentifier(0));
				temp.add(Itemset.oneItemsets.get(j).getIdentifier(0));
				candidateItemset.add(new Itemset(temp));
			}
		}
		for(Itemset itset:candidateItemset) {
			hm.put(itset, 0);
		}
		System.out.println("# 2 candidate itemsets: " + candidateItemset.size());
		for(String str:transaction) {
			for(Itemset itset:candidateItemset) {
				if(str.startsWith(itset.getIdentifier(0)) || str.endsWith(itset.getIdentifier(0))) {
					if(str.contains(itset.getIdentifier(1)))
						hm.replace(itset, hm.get(itset)+1);
				}
				else if (str.startsWith(itset.getIdentifier(1)) || str.endsWith(itset.getIdentifier(1))) {
					if(str.contains(itset.getIdentifier(0)))
						hm.replace(itset, hm.get(itset)+1);
				}
				else if(str.contains(" " + itset.getIdentifier(0) + " ") && str.contains(" " + itset.getIdentifier(1) + " ")) {
					hm.replace(itset, hm.get(itset)+1);
				}
			}
		}
		for(Itemset itset:hm.keySet()) {
			if(hm.get(itset) >= support)
				Itemset.twoItemsets.add(itset);
		}
		System.out.println("# 2 itemset: " + Itemset.twoItemsets.size());

	}
}
