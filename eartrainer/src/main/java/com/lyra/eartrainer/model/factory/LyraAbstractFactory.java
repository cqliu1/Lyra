package com.lyra.eartrainer.model.factory;

import com.lyra.eartrainer.model.instrument.IMusicInstrument;
import com.lyra.eartrainer.model.instrument.MusicInfo;

//TO DO: re-factor activity out of all of these factory classes
abstract public class LyraAbstractFactory {
	abstract public IMusicInstrument createInstrument(MusicInfo musicInfo);
}
