package com.lyra.eartrainer.factory.creators;

import android.app.Activity;

import com.lyra.eartrainer.factory.MusicInstrumentFactory;
import com.lyra.eartrainer.factory.abs.LyraAbstractFactory;
import com.lyra.eartrainer.model.globals.InstrumentTypes;
import com.lyra.eartrainer.model.globals.ScaleTypes;
import com.lyra.eartrainer.model.instrument.IMusicInstrument;

public class PentatonicConcreteCreator extends LyraAbstractFactory {
	 public IMusicInstrument createInstrument(Activity activity){
		 return MusicInstrumentFactory.makeInstrument(activity, InstrumentTypes.PIANO, ScaleTypes.PENTATONIC);
	 }
}
