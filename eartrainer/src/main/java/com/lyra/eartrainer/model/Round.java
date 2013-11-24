package com.lyra.eartrainer.model;

public class Round {
	private Note firstNote;
	private Note secondNote;
	private Note selectedNote;
	
	private int selectionCount;				//the number of times the player attempted to select the correct note
	private boolean finished;
	private int time;						//currentTime - startTime -- time it took to complete the round
	
	public Round(){
		Instrument instrument = GamePlay.instance().getInstrument();
		
	}
	
	//returns true if the player selected the correct result
	public boolean isCorrect(){
		return true; //remove this when we code it. Just getting rid of java error by returning something.
	}
}
