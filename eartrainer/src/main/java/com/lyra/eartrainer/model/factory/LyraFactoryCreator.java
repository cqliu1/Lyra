package com.lyra.eartrainer.model.factory;

import com.lyra.eartrainer.model.globals.InstrumentTypes;
import com.lyra.eartrainer.model.globals.ScaleTypes;


public class LyraFactoryCreator {
	public static LyraAbstractFactory getFactory(byte scale){
		LyraAbstractFactory factory = null;
		
		if(scale == ScaleTypes.MAJOR){
			factory = new PentatonicConcreteCreator();
		}
		else if(scale == ScaleTypes.PENTATONIC){
			factory = new MajorConcreteCreator();
		}
		
		return factory;
	}
}
