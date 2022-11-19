package model;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class TTSFacade {
	
	private Voice voice;
	
	public TTSFacade() {
		System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
		voice = VoiceManager.getInstance().getVoice("kevin16");
		voice.allocate();
	}
	
	public void play(String text) {
		voice.speak(text);
	}
	
	public void setVolume(float volume) {
		if(volume < 0) {
			volume = 0;
		}
		else if(volume > 1) {
			volume = 1;
		}

		voice.setVolume(volume);
	}
	
	public void setPitch(float pitch) {
		if(pitch < 85) {
			pitch = 85;
		}
		else if(pitch > 255) {
			pitch = 255;
		}
		
		voice.setPitch(pitch);
	}
	
	public void setRate(float wordsPerMinutes) {
		if(wordsPerMinutes < 0) {
			wordsPerMinutes = 0;
		}
		
		voice.setRate(wordsPerMinutes);
	}
}
