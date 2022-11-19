package input;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class TextReader implements DocumentReader {

	@Override
	public ArrayList<String> read(String fullPath) {

		ArrayList<String> contents = new ArrayList<String>();

		try {
			File file = new File(fullPath);
			Scanner scanner = new Scanner(file);
			
			while (scanner.hasNextLine()) {
				contents.add(scanner.nextLine());
			}
			
			scanner.close();
		} catch (Exception e) {
			System.out.println("An error occurred while trying to read the text file.");
			e.printStackTrace();
		}

		return contents;
	}

}
