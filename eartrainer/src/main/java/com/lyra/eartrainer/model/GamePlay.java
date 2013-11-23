package com.lyra.eartrainer.model;

import java.util.ArrayList;

public class GamePlay {
	private byte state;
	private byte mode;
	private byte difficulty;
	private int numberOfSteps;			//the number of rounds you have
	private boolean isFirstRound;		
	private ArrayList<Round> rounds;
	private float score;
	private float volume;
	
	public GamePlay(){
		
	}
	
}
