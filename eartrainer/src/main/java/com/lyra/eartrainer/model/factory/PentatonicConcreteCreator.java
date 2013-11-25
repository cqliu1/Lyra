package com.lyra.eartrainer.model.factory;

import com.lyra.eartrainer.model.globals.InstrumentTypes;
import com.lyra.eartrainer.model.globals.ScaleTypes;
import com.lyra.eartrainer.model.instrument.IMusicInstrument;
import com.lyra.eartrainer.model.instrument.MusicInfo;

public class PentatonicConcreteCreator extends LyraAbstractFactory {
	 public IMusicInstrument createInstrument(MusicInfo musicInfo){
		 return MusicInstrumentFactory.makeInstrument(musicInfo, InstrumentTypes.PIANO, ScaleTypes.PENTATONIC);
	 }
}
