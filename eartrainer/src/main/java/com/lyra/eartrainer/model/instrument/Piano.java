package com.lyra.eartrainer.model.instrument;

import android.media.SoundPool;

public class Piano extends LyraInstrument implements ILyraInstrument {
	public Piano(SoundPool sp, int[] notes){
		super(sp, notes);
		minNote = 1;
		maxNote = 13;
	}
}
