package com.lyra.eartrainer.view;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.SeekBar;

import com.lyra.eartrainer.R;

public class Pause {
	private Button resume;
	private Button restart;
	private Button quit;
	private SeekBar volumeBar;
	private int volume = 50;
	
	public Pause(Activity activity){
		this.resume = (Button)activity.findViewById(R.id.btnResumeGame);
		this.restart = (Button)activity.findViewById(R.id.btnRestartGame);
		this.quit = (Button)activity.findViewById(R.id.btnQuitGame);
		this.volumeBar = (SeekBar)activity.findViewById(R.id.seekVolume);
		volumeBar.scrollTo(volume, 50);
	}
	public void updateVolume(int newVolume)
	{
		volume = newVolume;
		volumeBar.scrollTo(volume, 1);
	}
	
}
