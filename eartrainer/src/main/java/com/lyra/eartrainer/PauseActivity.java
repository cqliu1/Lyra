package com.lyra.eartrainer;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.Menu;

import com.lyra.eartrainer.control.PauseController;

public class PauseActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		PauseController pController = new PauseController(this);
		pController.initialize();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(com.lyra.eartrainer.R.menu.main, menu);
		return true;
	}
}
