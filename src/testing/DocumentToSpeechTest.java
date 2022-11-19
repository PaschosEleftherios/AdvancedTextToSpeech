package testing;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;

import org.junit.Before;
import org.junit.Test;

import commands.DocumentToSpeech;
import model.Document;
import model.FakeTTSFacade;

public class DocumentToSpeechTest {
	
	private static final String contentToPlay = "This is a test content";
	private static final float volume = 100;
	private static final float rate = 150;
	private static final float pitch = 120;
	
	private Document document;
	private DocumentToSpeech documentToSpeechCommand;
	
	@Before
	public void setUp() throws Exception {
		document = new Document(new FakeTTSFacade());
		document.addContents(contentToPlay);
		
		documentToSpeechCommand = new DocumentToSpeech(document);
	}

	@Test
	public void testWithTuning() {
		documentToSpeechCommand.actionPerformed(new ActionEvent(this, 1, "test"));
		
		document.tuneAudio(volume, rate, pitch);
		
		// Audio
		FakeTTSFacade fakeAudioManager = (FakeTTSFacade) document.getAudioManager();
		assertEquals(fakeAudioManager.getPlayedContents(), contentToPlay);
		
		// Tuning
		assertEquals(fakeAudioManager.getSavedVolume(), volume, 0.5);
		assertEquals(fakeAudioManager.getSavedRate(), rate, 0.5);
		assertEquals(fakeAudioManager.getSavedPitch(), pitch, 0.5);
	}
	
}
