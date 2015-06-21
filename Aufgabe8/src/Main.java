import java.util.ArrayList;


public class Main {

	public static void main(String[] args) {
		//create a File Reader to read the transactions
		FileRead fr = new FileRead("transactions.txt");
		//create string array and fill it with the transactions
		//--> each array element is one transaction,
		//i.e. every entry of transaction[] is a whitespace-separated list of article identifiers and thus
		//represents the articles acquired during a single transaction
		String[] transaction = new String[fr.getNumberOfLines()];
		for(int i = 0; i < transaction.length; i++)
			transaction[i] = fr.readLine();
		
		//minimal support should be 1%
		double support = transaction.length*0.01;
		
		Apriori apr = new Apriori(transaction);
		apr.create_1_itemsets(support);
		apr.find_frequen_2_itemsets(support);
		
	}

}
