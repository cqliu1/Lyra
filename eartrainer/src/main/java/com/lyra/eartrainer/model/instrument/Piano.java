package com.lyra.eartrainer.model.instrument;


public class Piano extends MusicInstrument implements IMusicInstrument {
	public Piano(MusicInfo musicInfo){
		super(musicInfo);
		minNote = 1;
		maxNote = 13;
	}
}
