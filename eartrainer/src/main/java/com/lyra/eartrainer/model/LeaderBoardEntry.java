package com.lyra.eartrainer.model;

public class LeaderBoardEntry {
	private int id;
	private int user_id;
	private int score;
	private int date;
	private byte difficulty;
	private byte instrument;
	private byte game;
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getDate() {
		return date;
	}
	public void setDate(int date) {
		this.date = date;
	}
	public byte getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(byte difficulty) {
		this.difficulty = difficulty;
	}
	public byte getInstrument() {
		return instrument;
	}
	public void setInstrument(byte instrument) {
		this.instrument = instrument;
	}
	public byte getGame() {
		return game;
	}
	public void setGame(byte game) {
		this.game = game;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
