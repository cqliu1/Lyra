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
		int max = instrument.getMaxNote() +1;
		
		// When we implement intervals, they will be plugged in here
		if (GamePlay.instance().getMode() == Modes.PRACTICE)
		{
			minHalfStep = GamePlay.instance().getLeftInterval();
			maxHalfStep = GamePlay.instance().getRightInterval();
		
			if (instrument2 == InstrumentTypes.GUITAR)
			{
				if (minHalfStep > 11)
					minHalfStep+=2;
				else if (minHalfStep > 5)
					minHalfStep+=1;
				if (maxHalfStep > 11)
					maxHalfStep+=2;
				else if (maxHalfStep > 5)
					maxHalfStep+=1;
			}
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
			if (GamePlay.instance().getHiLo() == HiLo.HiLo && GamePlay.instance().getMode() == Modes.PRACTICE
					&& firstNote > secondNote)
				swapNotes();
			else if (GamePlay.instance().getHiLo() == HiLo.LoHi && GamePlay.instance().getMode() == Modes.PRACTICE
					&&  secondNote > firstNote)
				swapNotes();
				
		} while(belowMinStep || aboveMaxStep || secondNote < 0 || secondNote > (max-min)
				|| (instrument2 == InstrumentTypes.GUITAR && ((secondNote%5 == 0 || secondNote%5==1)
						&& (firstNote%5 == 0 || firstNote%5 == 1))));
		
		finished = false;
		
	}
	
	//returns true if the player selected the correct result
	public boolean isCorrect(int note){
		byte instrument = GamePlay.instance().getInstrumentType();
		
		if(note == secondNote){ 
				return true;
		} else if(instrument == InstrumentTypes.GUITAR){ //check overlapping notes
			switch(secondNote) {
				case 5: //A on E string
					if(note == 6)
						return true;
					break;
				case 6: //open A
					if(note == 5)
						return true;
					break;
				case 11: //D on A string
					if(note == 12)
						return true;
					break;
				case 12: //open D
					if(note == 11)
						return true;
					break;
				case 17: //G on D string
					if(note == 18)
						return true;
					break;
				case 18: //open G
					if(note == 17)
						return true;
					break;
				case 22: //B on G string
					if(note == 24)
						return true;
					break;
				case 23: //C on G string
					if(note == 25)
						return true;
					break;
				case 24: //open B
					if(note == 22)
						return true;
					break;
				case 25: //C on B string
					if(note == 23)
						return true;
					break;	
				case 29: //E on B string
					if(note == 30)
						return true;
					break;
				case 30: //open E
					if(note == 29)
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
	
	public int getInterval() {
		int returnVal = Math.abs(firstNote-secondNote);
		if (GamePlay.instance().getInstrumentType() == InstrumentTypes.GUITAR)
		{
			if (returnVal > 11)
				returnVal-=2;
			else if (returnVal > 5)
				returnVal--;
		}
		//Sean - Add logic to subtract the right amount so that the interval is correct.
		
		return returnVal;
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
