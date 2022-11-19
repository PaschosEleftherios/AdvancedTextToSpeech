package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ReplayManager {

	private static ReplayManager instance;
	
	private ArrayList<ActionListener> commands;
	private boolean isActiveRecording;
	
	public ReplayManager() {
		commands = new ArrayList<ActionListener>();
		isActiveRecording = false;
	}
	
	public static ReplayManager getInstance() {
		if(instance == null)
			instance = new ReplayManager();
		
		return instance;
	}
	
	public void startRecording() {
		if (isActiveRecording) {
			// Already recording
			System.out.println("Already recording.");
			return;
		}
		
		// Start recording
		commands.clear();
		isActiveRecording = true;
	}
	
	public void endRecording() {
		if (!isActiveRecording) {
			// Doesn't recording
			System.out.println("Doesn't recording.");
			return;
		}
		
		// Stop recording
		isActiveRecording = false;
	}
	
	public void tryToRecordCommand(ActionListener command) {
		if (!isActiveRecording) {
			// Doesn't recording
			return;
		}
		
		commands.add(command);
		System.out.println("The command was recorded. Total " + commands.size() + " commands recorded.");
	}

	public void replay() {
		if (commands == null || commands.size() == 0) {
			System.out.println("Nothing to replay.");
			return;
		}
		
		for(int i = 0; i < commands.size() ; i++) {
			ActionListener command = commands.get(i);
			command.actionPerformed(new ActionEvent(this, i, "ReplayCommand" + i));
		}
	}

	public boolean isActiveRecording() {
		return isActiveRecording;
	}
	
}
