package testing;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;

import org.junit.Before;
import org.junit.Test;

import commands.EndRecording;
import commands.ReplayManager;
import commands.StartRecording;

public class ReplayManagerTest {
	
	@Before
	public void setUp() throws Exception {

	}
	
	@Test
	public void testStartRecording() {
		StartRecording startRecordingCommand = new StartRecording();
		startRecordingCommand.actionPerformed(new ActionEvent(this, 1, "testStart"));
		
		assertEquals(ReplayManager.getInstance().isActiveRecording(), true);
	}
	
	@Test
	public void testStopRecording() {
		StartRecording startRecordingCommand = new StartRecording();
		startRecordingCommand.actionPerformed(new ActionEvent(this, 1, "testStart"));
		
		EndRecording endRecordingCommand = new EndRecording();
		endRecordingCommand.actionPerformed(new ActionEvent(this, 1, "testStop"));
		
		assertEquals(ReplayManager.getInstance().isActiveRecording(), false);
	}
}
