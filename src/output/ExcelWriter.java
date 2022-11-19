package output;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.*;

public class ExcelWriter implements DocumentWriter {

	@Override
	public void write(ArrayList<String> content, String fullpath) {
		try {
			FileOutputStream outputStream = new FileOutputStream(fullpath);
			XSSFWorkbook workbook = new XSSFWorkbook();

			XSSFSheet sheet = workbook.createSheet();
			
			int currentRow = 0;
			for(String line : content) {
				// Split the lines into tabs
				String[] tabs = line.split("\t");
				XSSFRow row = sheet.createRow(currentRow);
				
				int currentCell = 0;
				for(String tab : tabs) {
					// Make a cell for each tab
					XSSFCell cell = row.createCell(currentCell);
					cell.setCellValue(tab);
					currentCell++;
				}

				currentRow++;
			}
			
			workbook.write(outputStream);
			
			workbook.close();
			outputStream.close();
		} catch (IOException e) {
			System.out.println("An error occurred while trying to write to a xlsx file.");
			e.printStackTrace();
		}
	}

}
