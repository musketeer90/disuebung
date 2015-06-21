import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

/**
 * class responsible for reading data out of the specified files;
 */
public class FileRead {

	private BufferedReader reader;
	private LineNumberReader lnr;
	
	/**
	 * Set current working directory with directory
	 * @param directory
	 */
	FileRead(String filepath){
		
		try{
			this.reader = new BufferedReader(new InputStreamReader(new FileInputStream(filepath)));
			this.lnr = new LineNumberReader(new FileReader(new File(filepath)));
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public String readLine(){
		try{
			return reader.readLine();
		}catch(Exception e){
			e.printStackTrace();
			return e.toString();
		}
	}
	
	public int getNumberOfLines() {
		try {
			lnr.skip(Long.MAX_VALUE);
			return lnr.getLineNumber();
		}catch(Exception e){
			e.printStackTrace();
		}
		return -1;
	}

}
