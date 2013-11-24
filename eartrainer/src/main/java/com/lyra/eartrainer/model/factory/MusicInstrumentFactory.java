package com.lyra.eartrainer.model.factory;

import com.lyra.eartrainer.model.globals.InstrumentTypes;
import com.lyra.eartrainer.model.instrument.IMusicInstrument;
import com.lyra.eartrainer.model.instrument.MusicInfo;
import com.lyra.eartrainer.model.instrument.Piano;

public class MusicInstrumentFactory {
	
	public static IMusicInstrument makeInstrument(MusicInfo musicInfo, byte instrumentType, byte scale){
		IMusicInstrument instrument = null;
        
        if(instrumentType == InstrumentTypes.PIANO){
        	instrument = new Piano(musicInfo);
        }
        else if(instrumentType == InstrumentTypes.GUITAR){
        	//TO DO: for creating the guitar
        }
    	
		return instrument;
	}

}
