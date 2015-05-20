import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;
/**
 * class responsible for creating a new file
 * and writing output data in created file 
 */
public class FileWrite {
	static FileWriter fstream;
	static BufferedWriter out;
	/**
	 * created File, in which output data is written
	 */
	static File output;
	
	public FileWrite() {}
	/**
	 * creates a new File with the given directory
	 * @param outputDirectory
	 */
	public FileWrite(String outputDirectory) {

		output = new File(outputDirectory);
		try {
			fstream = new FileWriter(output);
			out = new BufferedWriter(fstream);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * writes given String into the created file
	 * @param output String, which is written to the file
	 */
	public static void writeOutput(String outputDirectory, String data) {
		try {
			output = new File(outputDirectory);
			fstream = new FileWriter(output);
			out = new BufferedWriter(fstream);
			out.write(data);
			out.newLine();
			out.flush();
			out.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}	
}
