package com.lyra.eartrainer.model.instrument;

import android.media.SoundPool;

public class MusicInfo {
	private SoundPool soundPool;
	private int[] soundNotes;
	
	public SoundPool getSoundPool() {
		return soundPool;
	}
	public void setSoundPool(SoundPool soundPool) {
		this.soundPool = soundPool;
	}
	public int[] getSoundNotes() {
		return soundNotes;
	}
	public void setSoundNotes(int[] soundNotes) {
		this.soundNotes = soundNotes;
	}
}
