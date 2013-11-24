package com.lyra.eartrainer.model;

public class Note {
	private int id;
	private String name;
	private int file;
	private int soundId;
	
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
	public int getFile() {
		return file;
	}
	public void setFile(int file) {
		this.file = file;
	}
	public int getSoundId() {
		return soundId;
	}
	public void setSoundId(int soundId) {
		this.soundId = soundId;
	}
}
