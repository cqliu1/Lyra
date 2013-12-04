package com.lyra.eartrainer.model;

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
		
		firstNote = (int) (Math.random() * (max-min));
		secondNote = firstNote;
		
		while(secondNote == firstNote) {
			secondNote = (int) (Math.random() * (max-min));
		}	
		
	}
	
	//returns true if the player selected the correct result
	public boolean isCorrect(int note){
		if(note == secondNote) {
			return true;
		} else {
			selectionCount++;
			return false;
		}
	}
	
	public int getFirstNote() {
		return firstNote;
	}
	
	public void playNotes() {
		GamePlay.instance().getInstrument().playNote(firstNote);
		// Pause
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GamePlay.instance().getInstrument().playNote(secondNote);
	}
}
