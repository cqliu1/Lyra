package com.lyra.eartrainer.properties;

import java.io.FileNotFoundException;

import com.lyra.eartrainer.dao.PropertiesDao;

import android.app.Activity;

public class LyraProps {
	private static LyraProps instance;
	private UserPreferences prefs;
	private static Activity activity;
	
	private LyraProps(){
		prefs = new UserPreferences();
		
		//load the user prefs
		PropertiesDao propManager = new PropertiesDao(activity.getFilesDir());
		try {
			propManager.loadProperties(prefs);
		}
		catch(FileNotFoundException fnfE){
			//do nothing -- default options are loaded -- user prefs don't exist until first save
		}
		catch(Exception e){
			System.out.println("Exception when loading properties.");
			e.printStackTrace();
		}
	}
	
	public static synchronized LyraProps getInstance(Activity theActivity){
		activity = theActivity;
		if(instance == null)
			instance = new LyraProps();
		return instance;
	}
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException(); 
	}
	
	public UserPreferences getUserPreferences(){
		return prefs;
	}
	
	public void saveProps(){
		PropertiesDao propManager = new PropertiesDao(activity.getFilesDir());
		
		try {
			propManager.saveProperties(prefs);
		}
		catch(Exception e){
			System.out.println("Exception when saving properties.");
			e.printStackTrace();
		}
	}
}
