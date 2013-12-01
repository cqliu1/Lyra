package com.lyra.eartrainer.model.instrument;

import com.lyra.eartrainer.model.globals.InstrumentTypes;

public class MusicInstrumentFactory {
	
	public static IMusicInstrument makeInstrument(SoundInfo musicInfo, byte instrumentType, byte scale){
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
