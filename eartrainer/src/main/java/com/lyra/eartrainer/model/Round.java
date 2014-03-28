package com.lyra.eartrainer.model;

//import java.util.TimerTask;

//import android.app.Activity;

import android.util.Log;

import com.lyra.eartrainer.model.globals.InstrumentTypes;
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
		
		int min = instrument.getMinNote();
		int max = instrument.getMaxNote() +1;
		
		// When we implement intervals, they will be plugged in here
		int minHalfStep = 1;
		int maxHalfStep = 12;
		
		firstNote = (int) (Math.random() * (max-min));
		boolean belowMinStep = true, aboveMaxStep = true;
		
		do {
			secondNote = (int) (Math.random() * (max-min));
			System.out.println("Second note: " + secondNote);
			
			// Make sure the second note is at least minHalfStep away
			belowMinStep = Math.abs(secondNote-firstNote) < minHalfStep;
			aboveMaxStep = Math.abs(secondNote-firstNote) > maxHalfStep;
		} while(belowMinStep || aboveMaxStep || secondNote < 0 || secondNote > (max-min));
		
		finished = false;
		
	}
	
	//returns true if the player selected the correct result
	public boolean isCorrect(int note){
		byte instrument = GamePlay.instance().getInstrumentType();
		
		if(note == secondNote){ 
				return true;
		} else if(instrument == InstrumentTypes.GUITAR){ //check overlapping notes
			switch(secondNote+1) {
				case 6: //A on E string
					if(note == 7)
						return true;
					break;
				case 7: //open A
					if(note == 6)
						return true;
					break;
				case 12: //D on A string
					if(note == 13)
						return true;
					break;
				case 13: //open D
					if(note == 12)
						return true;
					break;
				case 18: //G on D string
					if(note == 19)
						return true;
					break;
				case 19: //open G
					if(note == 18)
						return true;
					break;
				case 23: //B on G string
					if(note == 25)
						return true;
					break;
				case 24: //C on G string
					if(note == 26)
						return true;
					break;
				case 25: //open B
					if(note == 23)
						return true;
					break;
				case 26: //C on B string
					if(note == 24)
						return true;
					break;	
				case 30: //E on B string
					if(note == 31)
						return true;
					break;
				case 31: //open E
					if(note == 30)
						return true;
					break;
				default:
						selectionCount++;
						return false;
			}
		}
		
		selectionCount++;
		return false;
	}
	
	public int getFirstNote() {
		return firstNote;
	}

	public int getSecondNote() {
		return secondNote;
	}
	
	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	
	public boolean getFinished() {
		return finished;
	}
}
