package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartRecording implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent event) {
		ReplayManager.getInstance().startRecording();
	}

}
