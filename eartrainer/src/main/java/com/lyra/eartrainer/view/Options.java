package com.lyra.eartrainer.view;

import android.app.Activity;
import android.widget.RadioGroup;

import com.lyra.eartrainer.R;
import com.lyra.eartrainer.model.globals.InstrumentTypes;
import com.lyra.eartrainer.model.globals.Modes;
import com.lyra.eartrainer.properties.LyraProps;
import com.lyra.eartrainer.model.globals.ScaleTypes;
import com.lyra.eartrainer.model.globals.HiLo;

 public class Options {
   public Options(Activity activity) {
	   //setting the view options based on the users' preferences
	   int gameModeButtonId = 0, instrumentButtonId = 0, hiloButtonId = 0, scaleTypeLowButtonId = 0, scaleTypeHighButtonId = 0;
	   
	   byte gameMode = LyraProps.getInstance(activity).getUserPreferences().getGameMode();
	   if(gameMode == Modes.CHALLENGE)
		   gameModeButtonId = R.id.challenge_option;
	   else if(gameMode == Modes.PRACTICE)
		   gameModeButtonId = R.id.practice_option;
	   else
		   gameModeButtonId = R.id.freeplay_option;
	   
	   RadioGroup gameModeGroup = (RadioGroup)activity.findViewById(R.id.game_mode_group);
	   gameModeGroup.check(gameModeButtonId);
	   
	   byte instrumentType = LyraProps.getInstance(activity).getUserPreferences().getInstrumentType();
	   if(instrumentType == InstrumentTypes.GUITAR)
		   instrumentButtonId = R.id.guitar_option;
	   else
		   instrumentButtonId = R.id.piano_option;
	   
	   RadioGroup instrumentGroup = (RadioGroup)activity.findViewById(R.id.instrument_group);
	   instrumentGroup.check(instrumentButtonId);
	   
	   RadioGroup hiloGroup = (RadioGroup)activity.findViewById(R.id.hi_lo_group);
	   
	   
	   
   }
}
