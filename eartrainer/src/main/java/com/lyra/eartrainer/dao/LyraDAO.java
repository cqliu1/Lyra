package com.lyra.eartrainer.dao;

public interface LyraDAO {
	public String getLocation();
	public void setLocation(String location);
	public String getBaseURI();
	public void setBaseURI(String baseURI);
	public int getResponseStatusCode();
}
