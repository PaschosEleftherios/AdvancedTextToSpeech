package output;

public class DocumentWriterFactory {

	public static DocumentWriter createWriter(String fileType) {
		if (fileType == null) {
			throw new IllegalArgumentException();
		}
		
		if (fileType.equals("txt")) {
			return new TextWriter();
		}
		
		if (fileType.equals("docx")) {
			return new WordWriter();
		}
		
		if (fileType.equals("xlsx")) {
			return new ExcelWriter();
		}

		throw new IllegalArgumentException();
		
	}
	
}
