package com.lyra.eartrainer.properties;

import com.lyra.eartrainer.model.globals.InstrumentTypes;
import com.lyra.eartrainer.model.globals.Modes;
import com.lyra.eartrainer.model.globals.HiLo;
import com.lyra.eartrainer.model.globals.ScaleTypes;

public class UserPreferences {
	private byte gameMode;
	private byte instrumentType;
	private byte hiOrLo;
	private byte leftInterval;
	private byte rightInterval;
	private boolean shownKeyNotes;
	
	
	public UserPreferences(){
		gameMode = Modes.FREEPLAY;
		instrumentType = InstrumentTypes.PIANO;
		shownKeyNotes = true;
		hiOrLo = HiLo.LoHi;
		leftInterval = ScaleTypes.m2;
		rightInterval = ScaleTypes.O;
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
	public byte getHiOrLo() {
		return hiOrLo;
	}
	public void setHiOrLo(byte hiOrLo) {
		this.hiOrLo = hiOrLo;
	}
	public byte getLeftInterval(){
		return leftInterval;
	}
	public void setLeftInterval(byte leftInterval) {
		this.leftInterval = leftInterval;
	}
	public byte getRightInterval(){
		return rightInterval;
	}
	public void setRightInterval(byte rightInterval) {
		this.rightInterval = rightInterval;
	}
}
