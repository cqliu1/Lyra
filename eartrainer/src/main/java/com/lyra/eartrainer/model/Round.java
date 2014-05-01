package com.lyra.eartrainer.model;

//import java.util.TimerTask;

//import android.app.Activity;

import android.util.Log;

import java.lang.Math;

import com.lyra.eartrainer.model.globals.HiLo;
import com.lyra.eartrainer.model.globals.InstrumentTypes;
import com.lyra.eartrainer.model.globals.Modes;
import com.lyra.eartrainer.model.instrument.IMusicInstrument;

public class Round {
	private int firstNote;
	private int secondNote;
	private int selectedNote;
	
	private int selectionCount;				//the number of times the player attempted to select the correct note
	private boolean finished;
	private int time;						//currentTime - startTime -- time it took to complete the round
	
	public Round(){
		IMusicInstrument instrument = GamePlay.instance().getInstrument();
		byte instrument2 = GamePlay.instance().getInstrumentType();
		int minHalfStep, maxHalfStep;
		
		
		int min = instrument.getMinNote();
		int max = instrument.getMaxNote()+1;
		
		if (GamePlay.instance().getInstrumentType() == InstrumentTypes.GUITAR) {
			min = getGuitarRealId(min);
			max = getGuitarRealId(max);
		}
		
		// When we implement intervals, they will be plugged in here
		if (GamePlay.instance().getMode() == Modes.PRACTICE)
		{
			minHalfStep = GamePlay.instance().getLeftInterval();
			maxHalfStep = GamePlay.instance().getRightInterval();
		
		}
		else
		{
			minHalfStep = 1;
			maxHalfStep = 12;
		}
		
		firstNote = (int) (Math.random() * (max-min));
		boolean belowMinStep = true, aboveMaxStep = true;
		
		do {
			secondNote = (int) (Math.random() * (max-min));
			//System.out.println("Second note: " + secondNote);
			
			// Make sure the second note is at least minHalfStep away
			belowMinStep = Math.abs(secondNote-firstNote) < minHalfStep;
			aboveMaxStep = Math.abs(secondNote-firstNote) > maxHalfStep;

				
		} while(belowMinStep || aboveMaxStep || secondNote < 0 || secondNote > (max-min));
		
		finished = false;
		
	}
	
	//returns true if the player selected the correct result
	public boolean isCorrect(int note){
		byte instrument = GamePlay.instance().getInstrumentType();
	
		
		if(instrument == InstrumentTypes.GUITAR){
			int actualNote = getGuitarRealId(note);
			selectionCount++;
			return actualNote == secondNote;
		} else if(instrument == InstrumentTypes.PIANO)
		{
			selectionCount++;
			return note == secondNote;
		}
		
		selectionCount++;
		return false;
	}
	
	public int getFirstNote() {
		// For guitar convert the note id
		if (GamePlay.instance().getInstrumentType() == InstrumentTypes.GUITAR) {
			return getGuitarFakeId(firstNote);
		}
		return firstNote;
	}

	public int getSecondNote() {
		// For guitar, convert the note id
		if (GamePlay.instance().getInstrumentType() == InstrumentTypes.GUITAR) {
			return getGuitarFakeId(secondNote);
		}
		return secondNote;
	}
	
	public int getInterval() {
		int returnVal = Math.abs(firstNote-secondNote);
		
		return returnVal;
	}
	
	// This converts a key'd ID from the UI to the internal id representation for guitar
	public int getGuitarRealId(int id) {
		// Fifth open string, fifth fret, offset by 6
		if(id > 30) {
			return id-6;
		} else
		// Fourth open string on fourth fret, offset by 5
		if(id > 24) {
			return id-5;
		} else
		// Third open string, offset by 3
		if(id > 18) {
			return id-3;
		} else
		// Second open string, offset by 2
		if(id > 12) {
			return id-2;
		} else
		// First open string, so offset by 1
		if(id > 6) {
			return id-1;
		} else
		// Set the note from musicInfo
		if(id <= 6) {
			return id;
		}
		return -1;
	}
	
	// This converts the internal representation of ID to UI representation for guitar, accounting for repeated keys
	public int getGuitarFakeId(int id) {
		// Fifth open string, fifth fret, offset by 6
		if(id > 24) {
			return id+6;
		} else
		// Fourth open string on fourth fret, offset by 5
		if(id > 19) {
			return id+5;
		} else
		// Third open string, offset by 3
		if(id > 15) {
			return id+3;
		} else
		// Second open string, offset by 2
		if(id > 10) {
			return id+2;
		} else
		// First open string, so offset by 1
		if(id > 5) {
			return id+1;
		} else {
			return id;
		}
	}
	
	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	
	public boolean getFinished() {
		return finished;
	}
	
	public void swapNotes() {
		int temp = firstNote;
		firstNote = secondNote;
		secondNote = temp;
	}
}
