package output;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class WordWriter implements DocumentWriter {

	@Override
	public void write(ArrayList<String> content, String fullpath) {
		try {

			FileOutputStream outputStream = new FileOutputStream(fullpath);
			XWPFDocument docx = new XWPFDocument();

			for(String line : content) {
				XWPFParagraph paragraph = docx.createParagraph();
				XWPFRun run = paragraph.createRun();
				run.setText(line + System.lineSeparator());
			}
			
			docx.write(outputStream);
			
			docx.close();
			outputStream.close();
			
		} catch (IOException e) {
			System.out.println("An error occurred while trying to write to a Doc file.");
			e.printStackTrace();
		}
	}

}
