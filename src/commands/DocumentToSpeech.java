package commands;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import commands.CommandsFactory.Command;
import model.Document;
import view.Text2SpeechEditorView;

public class DocumentToSpeech implements ActionListener {

	private Document document;
	
	public DocumentToSpeech(Document document) {
		this.document = document;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		if (document == null) {
			System.out.print("NULL");
		}
		
		// Tune audio settings
		Text2SpeechEditorView window = Text2SpeechEditorView.getInstance();
		
		if (window != null) {
			float volume = Text2SpeechEditorView.getInstance().getVolume();
			float rate = Text2SpeechEditorView.getInstance().getRate();
			float pitch = Text2SpeechEditorView.getInstance().getPitch();
			
			document.tuneAudio(volume, rate, pitch);
		}
		
		// Play audio
		document.playContents();

		if(!document.isEmpty()) {
			// Save contents
			ActionListener save = CommandsFactory.createCommand(Command.SAVE_DOCUMENT, document);
			save.actionPerformed(event);
		}

		// Save command on ReplayManager
		ReplayManager.getInstance().tryToRecordCommand(this);
	}

}
