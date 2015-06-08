import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * class responsible for reading data out of the specified files;
 */
public class FileRead {

	private BufferedReader reader;
	
	/**
	 * Set current working directory with directory
	 * @param directory
	 */
	FileRead(String filepath){
		
		try{
			this.reader = new BufferedReader(new InputStreamReader(new FileInputStream(filepath)));
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public String readData(){
		try{
			return reader.readLine();
		}catch(Exception e){
			e.printStackTrace();
			return e.toString();
		}
		
	}

}
