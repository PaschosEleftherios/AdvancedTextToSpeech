package output;

import java.util.ArrayList;

public interface DocumentWriter {

	void write(ArrayList<String> content, String fullpath);
	
}
