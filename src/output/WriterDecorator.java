package output;

import java.util.ArrayList;

public class WriterDecorator  implements DocumentWriter {

	protected DocumentWriter componentWriter;
	
	public WriterDecorator(DocumentWriter componentWriter) {
		this.componentWriter = componentWriter;
	}

	@Override
	public void write(ArrayList<String> content, String fullpath) {
		componentWriter.write(content, fullpath);
	}
	
}
