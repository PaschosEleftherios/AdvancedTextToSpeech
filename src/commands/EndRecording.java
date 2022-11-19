package commands;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndRecording implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent event) {
		ReplayManager.getInstance().endRecording();
	}

}
