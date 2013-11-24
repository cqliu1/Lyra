package com.lyra.eartrainer.model.instrument;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;

import com.lyra.eartrainer.R;
import com.lyra.eartrainer.model.globals.InstrumentType;

public class InstrumentFactory {
	
	public static ILyraInstrument makeInstrument(Activity activity, byte instrumentType){
		ILyraInstrument instrument = null;
		
		SoundPool sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);

        int[] notes = new int[13];
        notes[0] = sp.load(activity, R.raw.p040, 1); 
        notes[1] = sp.load(activity, R.raw.p041, 1); 
        notes[2] = sp.load(activity, R.raw.p042, 1); 
        notes[3] = sp.load(activity, R.raw.p043, 1); 
        notes[4] = sp.load(activity, R.raw.p044, 1); 
        notes[5] = sp.load(activity, R.raw.p045, 1); 
        notes[6] = sp.load(activity, R.raw.p046, 1); 
        notes[7] = sp.load(activity, R.raw.p047, 1); 
        notes[8] = sp.load(activity, R.raw.p048, 1); 
        notes[9] = sp.load(activity, R.raw.p049, 1); 
        notes[10] = sp.load(activity, R.raw.p050, 1); 
        notes[11] = sp.load(activity, R.raw.p051, 1); 
        notes[12] = sp.load(activity, R.raw.p052, 1);
        
        if(instrumentType == InstrumentType.PIANO){
        	instrument = new Piano(sp, notes);
        }
        else if(instrumentType == InstrumentType.GUITAR){
        	//TO DO: for creating the guitar
        }
    	
		return instrument;
	}

}
