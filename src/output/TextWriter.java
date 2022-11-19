package output;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TextWriter implements DocumentWriter {

	@Override
	public void write(ArrayList<String> content, String fullpath) {
		try {
			FileWriter writer = new FileWriter(fullpath);
			
			for(String line : content) {
				writer.append(line);
				writer.append("\n");
			}
			
			writer.close();
		} catch (IOException e) {
			System.out.println("An error occurred while trying to write to a text file.");
			e.printStackTrace();
		}
	}

}
