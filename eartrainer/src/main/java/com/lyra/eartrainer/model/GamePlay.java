package com.lyra.eartrainer.model;

import java.util.ArrayList;

public class GamePlay {
	private byte state;
	private byte mode;
	private byte difficulty;
	private int numberOfRounds;			//the number of rounds you have
	private boolean isFirstRound;		
	private ArrayList<Round> rounds;
	private int score;
	private float volume;
	
	public GamePlay(){
		score = 0;
		volume = 0.5f;
	}

	public byte getState() {
		return state;
	}

	public void setState(byte state) {
		this.state = state;
	}

	public byte getMode() {
		return mode;
	}

	public void setMode(byte mode) {
		this.mode = mode;
	}

	public byte getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(byte difficulty) {
		this.difficulty = difficulty;
	}

	public int getNumberOfRounds() {
		return numberOfRounds;
	}

	public void setNumberOfRounds(int numberOfRounds) {
		this.numberOfRounds = numberOfRounds;
	}

	public boolean isFirstRound() {
		return isFirstRound;
	}

	public void setFirstRound(boolean isFirstRound) {
		this.isFirstRound = isFirstRound;
	}

	public ArrayList<Round> getRounds() {
		return rounds;
	}

	public void setRounds(ArrayList<Round> rounds) {
		this.rounds = rounds;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public float getVolume() {
		return volume;
	}

	public void setVolume(float volume) {
		this.volume = volume;
	}
	
}
