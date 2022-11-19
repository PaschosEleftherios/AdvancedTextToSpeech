package commands;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import model.Document;
import view.Text2SpeechEditorView;

public class OpenDocument implements ActionListener {

	private Document document;
	
	public OpenDocument(Document document) {
		this.document = document;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		// Open the file Chooser dialog
		final JFileChooser fileChooser = new JFileChooser();
		int userSelection = fileChooser.showOpenDialog(fileChooser);
		if (userSelection != JFileChooser.APPROVE_OPTION) {
            System.out.println("Error opening the file chooser dialog");
            return;
        } 
		
		// Get the selected file
        File file = fileChooser.getSelectedFile();
        
        // Checks
        if (!file.isFile()) {
        	System.out.println("This is not a file");
            return;
        }
        
        if (!file.canRead()) {
        	System.out.println("The file is not readable!");
            return;
        }
        
        // Open the document
		String fullPath = file.getPath();
		document.open(fullPath);
		
		// Print the contents
		Text2SpeechEditorView.getInstance().setText(document.toString());
		
		// Save command on ReplayManager
		ReplayManager.getInstance().tryToRecordCommand(this);
	}

}
