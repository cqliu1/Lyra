package com.lyra.eartrainer.model.instrument;

import android.util.Log;

import com.lyra.eartrainer.model.Note;

public class Guitar extends MusicInstrument implements IMusicInstrument {
	public Guitar(SoundInfo musicInfo){
		super(musicInfo);
		
		// TODO: these values probably need to change
		minNote = 1;
		maxNote = 36;
		
		// Load the Guitar notes from the musicInfo
		int[] soundNotes = musicInfo.getSoundNotes();

		notes = new Note[maxNote-minNote+1];
		
//		if(soundNotes.length < notes.length) {
//			Log.e("Guitar", "Only " + soundNotes.length + " soundNotes but " + notes.length + " Notes");
//		}
		
		
		for(int i=0; i<=(maxNote-minNote); i++) {
			notes[i] = new Note();
			notes[i].setId(i+minNote);
			
			// TODO: UPDATE ALL OF THIS 
			// no idea what these notes are, can't change them...
			switch(notes[i].getId()) {
			case 1:							//open e
			case 15:
			case 30:
			case 31:						//open e
				notes[i].setName("E");
				break;
			case 2:
			case 16:
			case 32:
				notes[i].setName("F");
				break;
			case 3:
			case 17:
			case 33:
				notes[i].setName("F#/Gb");
				break;
			case 4:
			case 18:
			case 19:						// open g
			case 34:
				notes[i].setName("G");
				break;
			case 5:
			case 20:
			case 35:
				notes[i].setName("G#/Ab");
				break;
			case 6:
			case 7:							// open a
			case 21:
			case 36:
				notes[i].setName("A");
				break;
			case 8:
			case 22:
				notes[i].setName("A#/Bb");
				break;
			case 9:
			case 23:
			case 25:						//open b
				notes[i].setName("B");
				break;
			case 10:
			case 24:
			case 26:
				notes[i].setName("C");
				break;
			case 11:
			case 27:
				notes[i].setName("C#/Db");
				break;
			case 12:
			case 13:						// open d
			case 28:
				notes[i].setName("D");
				break;
			case 14:
			case 29:
				notes[i].setName("D#/Eb");
				break;
			}
			
			// Set the note from musicInfo
//			notes[i].setSoundId(soundNotes[i]);
		}	
	}
}
