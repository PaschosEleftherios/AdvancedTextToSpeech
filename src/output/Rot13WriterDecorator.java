package output;

import java.util.ArrayList;

public class Rot13WriterDecorator extends WriterDecorator{

	public Rot13WriterDecorator(DocumentWriter componentWriter) {
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
