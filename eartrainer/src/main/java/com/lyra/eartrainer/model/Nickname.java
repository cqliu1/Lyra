package com.lyra.eartrainer.model;


public class Nickname {
	private String name;
	private int id;
	
	public Nickname(){
		this.name = "";
		this.id = -1;
	}
	public Nickname(String name){
		this.name = name;
		this.id = -1;
	}
	public Nickname(int id, String name){
		this.name = name;
		this.id = id;
	}
	
	//getters & setters
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
