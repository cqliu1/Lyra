package com.lyra.eartrainer.properties;

import com.lyra.eartrainer.model.globals.InstrumentTypes;
import com.lyra.eartrainer.model.globals.Modes;

public class UserPreferences {
	private byte gameMode;
	private byte instrumentType;
	private boolean shownKeyNotes;
	
	public UserPreferences(){
		gameMode = Modes.FREEPLAY;
		instrumentType = InstrumentTypes.PIANO;
		shownKeyNotes = true;
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
	public boolean isShownKeyNotes() {
		return shownKeyNotes;
	}
	public void setShownKeyNotes(boolean shownKeyNotes) {
		this.shownKeyNotes = shownKeyNotes;
	}
}
