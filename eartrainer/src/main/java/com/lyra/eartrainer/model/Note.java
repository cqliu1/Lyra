package com.lyra.eartrainer.model;

public class Note {
	private int id;
	private String name;
	private int soundId;
	
	public Note() {
		this.id = -1;
		this.name = null;
		this.soundId = -1;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSoundId() {
		return soundId;
	}
	public void setSoundId(int soundId) {
		this.soundId = soundId;
	}
}
