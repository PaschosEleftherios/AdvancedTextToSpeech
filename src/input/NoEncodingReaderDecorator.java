package input;

import java.util.ArrayList;

public class NoEncodingReaderDecorator extends ReaderDecorator{

	public NoEncodingReaderDecorator(DocumentReader componentReader) {
		super(componentReader);
	}

	@Override
	public ArrayList<String> read(String fullPath){
		return super.read(fullPath);
	}
	
}
