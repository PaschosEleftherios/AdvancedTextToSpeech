package commands;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;

import model.Document;
import view.Text2SpeechEditorView;

public class SaveDocumentAs implements ActionListener {

	private Document document;
	
	public SaveDocumentAs(Document document) {
		this.document = document;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// Create a file chooser
		JFrame parentFrame = new JFrame();
		 
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a path to save the document");   
		 
		// Add the file filters
		fileChooser.addChoosableFileFilter(new FileFilter() {
			   public String getDescription() {
			       return "Text file (.txt)";
			   }

			   public boolean accept(File f) {
			       if (f.isDirectory()) {
			           return true;
			       } else {
			           String filename = f.getName().toLowerCase();
			           return filename.endsWith(".txt");
			       }
			   }
			});
		fileChooser.addChoosableFileFilter(new FileFilter() {
			   public String getDescription() {
			       return "Word document (.docx)";
			   }

			   public boolean accept(File f) {
			       if (f.isDirectory()) {
			           return true;
			       } else {
			           String filename = f.getName().toLowerCase();
			           return filename.endsWith(".docx");
			       }
			   }
			});
		fileChooser.addChoosableFileFilter(new FileFilter() {
			   public String getDescription() {
			       return "Excel sheet (.xlsx)";
			   }

			   public boolean accept(File f) {
			       if (f.isDirectory()) {
			           return true;
			       } else {
			           String filename = f.getName().toLowerCase();
			           return filename.endsWith(".xlsx");
			       }
			   }
			});
		
		fileChooser.setName("New Document.txt");
		
		int userSelection = fileChooser.showSaveDialog(parentFrame);
		if (userSelection != JFileChooser.APPROVE_OPTION) {
		    System.out.println("The file didn't saved.");
		    return;
		}
		
	    File fileToSave = fileChooser.getSelectedFile();
	    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
		
	    // Change the document's path & save
	    document.setFullPath(fileToSave.getAbsolutePath());
		document.save(Text2SpeechEditorView.getInstance().getText());
		
		// Save command on ReplayManager
		ReplayManager.getInstance().tryToRecordCommand(this);
	}

}
