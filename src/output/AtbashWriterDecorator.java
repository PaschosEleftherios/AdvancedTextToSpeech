package output;

import java.util.ArrayList;

public class AtbashWriterDecorator extends WriterDecorator{

	public AtbashWriterDecorator(DocumentWriter componentWriter) {
		super(componentWriter);
	}

	@Override
	public void write(ArrayList<String> content, String fullpath) {
		// Encode
		ArrayList<String> encodedDocument = new ArrayList<String>();
		for(String line : content) {
			StringBuilder lineBuilder = new StringBuilder();
			
			for(char c : line.toCharArray()) {
				lineBuilder.append(mapCharacter(c));
			}
			
			encodedDocument.add(lineBuilder.toString());
		}
		
		// Write encoded content
		super.write(encodedDocument, fullpath);
	}
	
	public char mapCharacter(char charToEncode) {
		int atbashChar = charToEncode;
		
		if (charToEncode >= 'A' && charToEncode <= 'Z') {
			atbashChar = ('Z' - charToEncode) + 'A';
		}
		else if (charToEncode >='a' && charToEncode <='z') {
			atbashChar = ('z' - charToEncode) + 'a';
		}
		
		return (char)atbashChar;
	}
	
}
