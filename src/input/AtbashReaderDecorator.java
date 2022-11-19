package input;

import java.util.ArrayList;

public class AtbashReaderDecorator extends ReaderDecorator{

	public AtbashReaderDecorator(DocumentReader componentReader) {
		super(componentReader);
	}

	@Override
	public ArrayList<String> read(String fullPath){
		// Read the encoded content
		ArrayList<String> encodedDocument = super.read(fullPath);
		
		// Decode the content
		ArrayList<String> decodedDocument = new ArrayList<String>();
		for(String line : encodedDocument) {
			StringBuilder lineBuilder = new StringBuilder();
			
			for(char c : line.toCharArray()) {
				lineBuilder.append(mapCharacter(c));
			}
			
			decodedDocument.add(lineBuilder.toString());
		}
		
		// Return decoded content
		return decodedDocument;
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
