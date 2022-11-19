package input;

import java.util.ArrayList;

public class Rot13ReaderDecorator extends ReaderDecorator{

	public Rot13ReaderDecorator(DocumentReader componentReader) {
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
		if (charToEncode >= 'a' && charToEncode <= 'm') {
			charToEncode += 13;
		}else if (charToEncode >= 'n' && charToEncode <='z') {
			charToEncode -=13;
		}else if (charToEncode >= 'A' && charToEncode <= 'M') {
			charToEncode += 13;
		}else if (charToEncode >= 'N' && charToEncode <= 'Z') {
			charToEncode -=13;
		}
		
		return charToEncode;
	}
	
}
