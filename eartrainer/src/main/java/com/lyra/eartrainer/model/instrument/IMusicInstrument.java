package com.lyra.eartrainer.model.instrument;

public interface IMusicInstrument {
	public void playNote(int note);
	public int getMinNote();
	public int getMaxNote();
}
