package input;

public class DocumentReaderFactory {

	public static DocumentReader createReader(String fileType) {
		if (fileType == null) {
			throw new IllegalArgumentException();
		}
		
		if (fileType.equals("txt")) {
			return new TextReader();
		}
		
		if (fileType.equals("docx")) {
			return new WordReader();
		}
		
		if (fileType.equals("xlsx")) {
			return new ExcelReader();
		}

		throw new IllegalArgumentException("Cannot open that file.");
		
	}
	
}
