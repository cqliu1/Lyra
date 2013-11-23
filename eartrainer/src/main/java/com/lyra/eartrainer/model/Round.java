package com.lyra.eartrainer.model;

public class Round {
	Note firstNote;
	Note secondNote;
	Note selectedNote;
	
	int selectionCount;				//the number of times the player attempted to select the correct note
	boolean finished;
	int time;						//currentTime - startTime -- time it took to complete the round
	
	
	//returns true if the player selected the correct result
	public boolean isCorrect(){
		return true; //remove this when we code it. Just getting rid of java error by returning something.
	}
}
