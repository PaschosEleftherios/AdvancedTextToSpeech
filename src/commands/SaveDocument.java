package commands;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import commands.CommandsFactory.Command;
import model.Document;
import view.Text2SpeechEditorView;

public class SaveDocument implements ActionListener {

	private Document document;
	
	public SaveDocument(Document document) {
		this.document = document;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(!document.hasSavedPreviously()) {
			// Hasn't saved previously so the document doesn't have a path.
			// We must use SaveAs command first
			ActionListener saveAs = CommandsFactory.createCommand(Command.SAVE_AS_DOCUMENT, document);
			saveAs.actionPerformed(event);
			
			return;
		}
		
		// Save to the previous saved location
		document.save(Text2SpeechEditorView.getInstance().getText());
		
		// Save command on ReplayManager
		ReplayManager.getInstance().tryToRecordCommand(this);
	}

}
