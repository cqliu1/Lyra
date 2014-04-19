package com.lyra.eartrainer.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import com.lyra.eartrainer.model.globals.InstrumentTypes;
import com.lyra.eartrainer.model.globals.Modes;
import com.lyra.eartrainer.properties.UserPreferences;

public class PropertiesDao {
	private static final String PROPERTIES_FILE = "Lyra.properties";
	private File propDir;
	
	public PropertiesDao(File dir){
		propDir = dir;
	}
	
	private String getPropertiesFilePath() throws Exception {
		return propDir.getAbsolutePath() + "/" + PROPERTIES_FILE;
	}
	
	public void loadProperties(UserPreferences prefs) throws Exception {
		Properties properties = new Properties();
        String propFile = getPropertiesFilePath();
        
        FileInputStream fis = new FileInputStream(new File(propFile));
        properties.load(fis);
        fis.close();
        
        prefs.setGameMode(Byte.parseByte((String)properties.getProperty("user.pref.game.mode", Modes.FREEPLAY + "").trim()));
        prefs.setInstrumentType(Byte.parseByte((String)properties.getProperty("user.pref.game.instrument", InstrumentTypes.PIANO + "").trim()));
        prefs.setShownKeyNotes(((String)properties.getProperty("user.pref.game.showKeyNotes","true").trim()).equals("true") ? true : false);
	}
	
	public void saveProperties(UserPreferences prefs) throws Exception {
		Properties properties = new Properties();
        String propFile = getPropertiesFilePath();
        
        properties.put("user.pref.game.mode", prefs.getGameMode() + "");
        properties.put("user.pref.game.instrument", prefs.getInstrumentType() + "");
        properties.put("user.pref.game.showKeyNotes", prefs.isShownKeyNotes() + "");
        
        FileOutputStream fos = new FileOutputStream(new File(propFile));
        properties.store(fos,"Lyra Eartrainer Properties");
        fos.close();
	}
}
