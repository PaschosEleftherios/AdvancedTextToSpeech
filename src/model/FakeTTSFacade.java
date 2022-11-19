package model;


public class FakeTTSFacade extends TTSFacade{

	private String playedContents;
	private float savedVolume;
	private float savedRate;
	private float savedPitch;
	
	@Override
	public void play(String text) {
		playedContents = text;
		super.play(text);
	}

	@Override
	public void setVolume(float volume) {
		savedVolume = volume;
		super.setVolume(volume);
	}

	@Override
	public void setPitch(float pitch) {
		savedPitch = pitch;
		super.setPitch(pitch);
	}

	@Override
	public void setRate(float wordsPerMinutes) {
		savedRate = wordsPerMinutes;
		super.setRate(wordsPerMinutes);
	}
	
	// Getters
	public String getPlayedContents() {
		return playedContents.trim();
	}

	public float getSavedVolume() {
		return savedVolume;
	}

	public float getSavedRate() {
		return savedRate;
	}

	public float getSavedPitch() {
		return savedPitch;
	}
	
}
