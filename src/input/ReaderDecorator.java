package input;

import java.util.ArrayList;

public abstract class ReaderDecorator implements DocumentReader {

	protected DocumentReader componentReader;
	
	public ReaderDecorator(DocumentReader componentReader) {
		this.componentReader = componentReader;
	}
	
	@Override
	public ArrayList<String> read(String fullPath){
		return componentReader.read(fullPath);
	}
	
}
