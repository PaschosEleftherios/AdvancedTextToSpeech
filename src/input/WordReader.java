package input;

import java.io.FileInputStream;
import java.util.ArrayList;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

public class WordReader implements DocumentReader {

	@Override
	public ArrayList<String> read(String fullPath) {
		ArrayList<String> contents = new ArrayList<String>();

		try {
			FileInputStream inputStream = new FileInputStream(fullPath);
			
			XWPFDocument docx = new XWPFDocument(inputStream);
			
			for (XWPFParagraph paragraph : docx.getParagraphs()) {
				String[] lineSlitted = paragraph.getText().split(System.lineSeparator());
				
				for (String line : lineSlitted) {
					contents.add(line);
				}
			}
			
			docx.close();
			inputStream.close();
		} catch (Exception e) {
			System.out.println("An error occurred while trying to read the Doc file.");
			e.printStackTrace();
		}

		return contents;
	}

}
