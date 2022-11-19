package commands;


import java.awt.event.ActionListener;

import model.Document;


public class CommandsFactory {
	
	public enum Command{
		SAVE_DOCUMENT,
		SAVE_AS_DOCUMENT,
		OPEN_DOCUMENT,
		PLAY_AUDIO,
		START_RECORDING,
		END_RECORDING,
		REPLAY_COMMANDS
	}
	
	public static ActionListener createCommand(Command command, Document document) {
		switch(command) {
		case OPEN_DOCUMENT:
			return new OpenDocument(document);
		case SAVE_DOCUMENT:
			return new SaveDocument(document);
		case SAVE_AS_DOCUMENT:
			return new SaveDocumentAs(document);
		case PLAY_AUDIO:
			return new DocumentToSpeech(document);
		case START_RECORDING:
			return new StartRecording();
		case END_RECORDING:
			return new EndRecording();
		case REPLAY_COMMANDS:
			return new ReplayCommands();
		default:
			throw new IllegalArgumentException();
		}
	}
}
