package com.lyra.eartrainer.model.instrument;

import android.util.Log;

import com.lyra.eartrainer.model.Note;

public class Piano extends MusicInstrument implements IMusicInstrument {
	public Piano(MusicInfo musicInfo){
		super(musicInfo);
		minNote = 40;
		maxNote = 52;
		
		// Load the piano notes from the musicInfo
		int[] soundNotes = musicInfo.getSoundNotes();

		notes = new Note[maxNote-minNote+1];
		
		if(soundNotes.length < notes.length) {
			Log.e("Piano", "Only " + soundNotes.length + " soundNotes but " + notes.length + " Notes");
		}
		
		
		for(int i=0; i<=(maxNote-minNote); i++) {
			notes[i] = new Note();
			notes[i].setId(i+minNote);
			
			// Set the name of the note depending on its Id
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
	
	// Returns true if the key is black
	public boolean isBlackKey(int keyNumber) {		
		int adjustedNoteId = keyNumber + minNote;
		
		return (adjustedNoteId%12 == 0 ||
				adjustedNoteId%12 == 2 ||
				adjustedNoteId%12 == 5 ||
				adjustedNoteId%12 == 7 ||
				adjustedNoteId%12 == 10			
				);
				
		
	}
	
}
