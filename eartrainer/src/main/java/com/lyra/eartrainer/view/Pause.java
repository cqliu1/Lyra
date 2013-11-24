package com.lyra.eartrainer.view;

import android.app.Activity;
import android.widget.SeekBar;

import com.lyra.eartrainer.model.GamePlay;

public class Pause{
	private GamePlay game;
	private SeekBar volumeBar;
	//SharedPreferences sharedPref = context.getSharedPreferences("volume_val", Context.MODE_PRIVATE);
	//SharedPreferences.Editor editor = sharedPref.edit();
	public Pause(Activity activity){
		game = GamePlay.instance();
		volumeBar.scrollTo((int)game.getVolume()*100, 1);
	}
	
	// TODO might not be necessary
	public void updateVolume()
	{
		//PauseActivity pause = (PauseActivity) getContext();
		//pause.changeVolume(newVolume);
		//editor.putInt("@string/volume_val", newVolume);
		volumeBar.scrollTo((int)game.getVolume()*100, 1);
		
	}
	
}
