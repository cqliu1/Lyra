package com.lyra.eartrainer.factory.abs;

import com.lyra.eartrainer.factory.creators.MajorConcreteCreator;
import com.lyra.eartrainer.factory.creators.PentatonicConcreteCreator;
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
