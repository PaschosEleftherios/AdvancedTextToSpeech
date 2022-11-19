package output;

import java.util.ArrayList;

public class NoEncodingWriterDecorator extends WriterDecorator{

	public NoEncodingWriterDecorator(DocumentWriter componentWriter) {
		super(componentWriter);
	}

	@Override
	public void write(ArrayList<String> content, String fullpath) {
		super.write(content, fullpath);
	}
	
}
