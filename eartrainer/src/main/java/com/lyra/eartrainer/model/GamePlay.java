package com.lyra.eartrainer.model;

import com.lyra.eartrainer.model.instrument.IMusicInstrument;

public class GamePlay {
	private static GamePlay instance = null;
	private byte state;
	private byte mode;
	private byte difficulty;
	private byte instrumentType;
	private byte scale;
	private int numberOfRounds;					
	private boolean isFirstRound;		
	//private ArrayList<Round> rounds;
	private int roundNumber;
	private Round currentRound;
	private int score;
	private float volume;
	private Nickname nickname;
	private IMusicInstrument instrument;
	private int strikes;
	
	//hiding constructor - using singleton pattern
	private GamePlay(){
		nickname = new Nickname("Guest");
		reset();
	}

	public void reset() {
		roundNumber = 0;
		score = 0;
		volume = 0.5f;
		strikes = 0;
		this.setInstrument(null);
		numberOfRounds = 10;
		currentRound = null;
	}
	
	// used when we want to restart after game over screen
	public void softReset() {
		roundNumber = 0;
		score = 0;
		strikes = 0;
		currentRound = null;
	}
	
    //Begin singleton logic code ------------------------------------------------->
    
    public static GamePlay instance(){
        if(instance == null){
            instance = new GamePlay();
        }
        return instance;
    }
    
    //preventing the cloning (copying) of this single instance
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException(); 
    }
    
    //END of singleton logic code ------------------------------------------------>

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
	
	public byte getInstrumentType() {
		return instrumentType;
	}

	public void setInstrumentType(byte instrumentType) {
		this.instrumentType = instrumentType;
	}
	
	public byte getScale() {
		return scale;
	}
	
	public void setScale(byte newScale) {
		this.scale = newScale;
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
	
	public Round getCurrentRound() {
		return currentRound;
	}
	
	public void endCurrentRound() {
		currentRound = null;
	}
	
	public void startNewRound() {
		currentRound = new Round();
		roundNumber++;
		strikes = 0;
	}
	
	public boolean isGameOver() {
		// For when we limit the number of rounds
		//return (roundNumber == numberOfRounds || strikes == 3);
		
		// For when there is unlimited rounds
		return strikes == 3;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public void incrementScore(int score) {
		this.score = this.score + score;
	}

	public float getVolume() {
		return volume;
	}

	public void setVolume(float volume) {
		this.volume = volume;
	}

	public Nickname getNickname() {
		return nickname;
	}

	public void setNickname(Nickname nickname) {
		this.nickname = nickname;
	}
	
	public int getStrikes()
	{
		return strikes;
	}
	
	public void oneStrike()
	{
		strikes++;
	}

	public IMusicInstrument getInstrument() {
		return instrument;
	}

	public void setInstrument(IMusicInstrument instrument) {
		this.instrument = instrument;
	}	
}
