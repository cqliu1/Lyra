package com.lyra.eartrainer;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.lyra.eartrainer.control.PauseController;

public class PauseActivity extends Activity {
//	private Button resume;
//	private Button restart;
//	private Button quit;
///	private SeekBar volumeBar;
//	private int volume = 50;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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
