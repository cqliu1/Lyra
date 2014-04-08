package com.lyra.eartrainer.view;

import android.app.Activity;
import android.widget.SeekBar;

import com.lyra.eartrainer.R;
import com.lyra.eartrainer.control.LyraView;
import com.lyra.eartrainer.model.GamePlay;

public class Pause extends LyraView {
	private GamePlay game;
	private SeekBar volumeBar;
	//SharedPreferences sharedPref = context.getSharedPreferences("volume_val", Context.MODE_PRIVATE);
	//SharedPreferences.Editor editor = sharedPref.edit();
	public Pause(Activity act){
		super(act);
		game = GamePlay.instance();
		volumeBar = (SeekBar) activity.findViewById(R.id.seekVolume);
		//updateVolume();
		// THIS VVVVV IS BREAKING PAUSE
		//volumeBar.setProgress((int)(game.getVolume()*100));
		
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
