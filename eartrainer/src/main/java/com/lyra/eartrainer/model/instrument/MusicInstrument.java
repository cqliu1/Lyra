package com.lyra.eartrainer.model.instrument;

import java.util.ArrayList;

import com.lyra.eartrainer.model.Note;

import android.media.AudioManager;
import android.media.SoundPool;

public class MusicInstrument implements IMusicInstrument {
	protected int scaleType;
	protected String name; 			//The name of the instrument (e.g. piano, guitar)
	protected Note[] notes;				//The collection of notes to be played
	protected int minNote;
	protected int maxNote;
	protected SoundPool sp;
	
	public MusicInstrument(SoundPool sp, int[] soundNotes){
		this.sp = sp;
		sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
		notes = new Note[soundNotes.length];
		for(int i = 0;i < soundNotes.length;i++){
			notes[i] = new Note();
			//this.notes[i].setFile(file);
			//this.notes[i].setName(name);
			//this.notes[i].setId(id);
			notes[i].setSoundId(soundNotes[i]);
		}
	}
	
    public void playNote(int note) {
    	//		game.setScore(0);
		//Toast.makeText(game, note, Toast.LENGTH_SHORT).show();
		
		// this is where we set the volume
		//float vol = (float)newVolume/100;
		sp.play(notes[note].getSoundId(), 1.0f, 1.0f, 0, 0, 1.0f);
	}
}
