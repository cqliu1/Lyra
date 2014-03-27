package com.lyra.eartrainer.properties;

import com.lyra.eartrainer.model.globals.InstrumentTypes;
import com.lyra.eartrainer.model.globals.Modes;

public class UserPreferences {
	private byte gameMode;
	private byte instrumentType;
	
	public UserPreferences(){
		gameMode = Modes.FREEPLAY;
		instrumentType = InstrumentTypes.PIANO;
	}

	public byte getGameMode() {
		return gameMode;
	}
	public void setGameMode(byte gameMode) {
		this.gameMode = gameMode;
	}
	public byte getInstrumentType() {
		return instrumentType;
	}
	public void setInstrumentType(byte instrumentType) {
		this.instrumentType = instrumentType;
	}
}
