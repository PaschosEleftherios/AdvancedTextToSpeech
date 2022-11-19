package input;

import java.io.FileInputStream;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.*;

public class ExcelReader implements DocumentReader {

	@Override
	public ArrayList<String> read(String fullPath) {
		ArrayList<String> contents = new ArrayList<String>();

		try {
			FileInputStream inputStream = new FileInputStream(fullPath);
			
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

			if (workbook.getNumberOfSheets() == 0) {
				System.out.println("The excel file has no sheets");
				workbook.close();
				inputStream.close();
				return contents;
			}
			
			// Get the first sheet
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			// Get Rows
			int rowsCount = sheet.getPhysicalNumberOfRows();
			for(int rowIndex = 0; rowIndex < rowsCount; rowIndex++) {
				XSSFRow row = sheet.getRow(rowIndex);
				StringBuilder cellBuilder = new StringBuilder();
				
				// Get Cells
				int cellsCount = row.getPhysicalNumberOfCells();
				int lastCell = row.getLastCellNum();
				int cellsToSearch = cellsCount > lastCell ? cellsCount : lastCell;
				
				for(int cellIndex = 0; cellIndex  < cellsToSearch ; cellIndex ++) {
					XSSFCell cell = row.getCell(cellIndex);
					
					// Split the cells into tabs
					if (cellIndex != 0) {
						cellBuilder.append("\t");
					}
					
					if (cell == null) {
						continue;
					}
					
					// Add the value to cell builder
					cellBuilder.append(getCellValue(cell));
				}
				
				contents.add(cellBuilder.toString());
			}

			workbook.close();
			inputStream.close();
		} catch (Exception e) {
			System.out.println("An error occurred while trying to read the Excel file.");
			e.printStackTrace();
		}

		return contents;
	}
	
	private String getCellValue(XSSFCell cell) {
		switch (cell.getCellType()) {
		case NUMERIC: 
			return String.valueOf(cell.getNumericCellValue());
		case BOOLEAN:
			return String.valueOf(cell.getBooleanCellValue());
		default:
			return String.valueOf(cell.getStringCellValue());
		}
	}

}
