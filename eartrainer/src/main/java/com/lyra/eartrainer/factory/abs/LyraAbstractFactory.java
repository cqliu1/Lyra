package com.lyra.eartrainer.factory.abs;

import android.app.Activity;

import com.lyra.eartrainer.model.instrument.IMusicInstrument;

//TO DO: re-factor activity out of all of these factory classes
abstract public class LyraAbstractFactory {
	abstract public IMusicInstrument createInstrument(Activity activity);
}
