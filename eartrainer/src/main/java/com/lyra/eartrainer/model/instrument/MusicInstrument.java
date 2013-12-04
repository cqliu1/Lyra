package com.lyra.eartrainer.model.instrument;

import android.media.SoundPool;

import com.lyra.eartrainer.model.Note;
import com.lyra.eartrainer.model.GamePlay;

public class MusicInstrument implements IMusicInstrument {
	protected int scaleType;
	protected String name; 			//The name of the instrument (e.g. piano, guitar)
	protected Note[] notes;				//The collection of notes to be played
	protected int minNote;
	protected int maxNote;
	protected SoundPool sp;
	protected GamePlay game;
	
	public MusicInstrument(SoundInfo musicInfo){
		this.sp = musicInfo.getSoundPool();
		game = GamePlay.instance();
	}
	
    public void playNote(int note) {
    	//		game.setScore(0);
		//Toast.makeText(game, note, Toast.LENGTH_SHORT).show();
		
		// this is where we set the volume
		//float vol = (float)newVolume/100;
    	float vol = game.getVolume();
		sp.play(notes[note].getSoundId(), vol, vol, 0, 0, 1.0f);
	}
    
    public int getMinNote() {
    	return minNote;
    }
    
    public int getMaxNote() {
    	return maxNote;
    }
}
