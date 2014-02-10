package com.lyra.eartrainer.model.instrument;

import android.util.Log;

import com.lyra.eartrainer.model.Note;

public class Guitar extends MusicInstrument implements IMusicInstrument {
	public Guitar(SoundInfo musicInfo){
		super(musicInfo);
		
		// TODO: these values probably need to change
		minNote = 40;
		maxNote = 52;
		
		// Load the Guitar notes from the musicInfo
		int[] soundNotes = musicInfo.getSoundNotes();

		notes = new Note[maxNote-minNote+1];
		
		if(soundNotes.length < notes.length) {
			Log.e("Guitar", "Only " + soundNotes.length + " soundNotes but " + notes.length + " Notes");
		}
		
		
		for(int i=0; i<=(maxNote-minNote); i++) {
			notes[i] = new Note();
			notes[i].setId(i+minNote);
			
			// TODO: UPDATE ALL OF THIS 
			// no idea what these notes are, can't change them...
			switch(notes[i].getId()%12) {
			case 4:
				notes[i].setName("C");
				break;
			case 5:
				notes[i].setName("C#/Db");
				break;
			case 6:
				notes[i].setName("D");
				break;
			case 7:
				notes[i].setName("D#/Eb");
				break;
			case 8:
				notes[i].setName("E");
				break;
			case 9:
				notes[i].setName("F");
				break;
			case 10:
				notes[i].setName("F#/Gb");
				break;
			case 11:
				notes[i].setName("G");
				break;
			case 0:
				notes[i].setName("G#/Ab");
				break;
			case 1:
				notes[i].setName("A");
				break;
			case 2:
				notes[i].setName("A#/Bb");
				break;
			case 3:
				notes[i].setName("B");
				break;
			}
			
			// Set the note from musicInfo
			notes[i].setSoundId(soundNotes[i]);
		}	
	}
}
