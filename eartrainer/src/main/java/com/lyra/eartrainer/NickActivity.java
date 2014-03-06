package com.lyra.eartrainer;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.Menu;

import com.lyra.eartrainer.control.NicknameController;

public class NickActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		NicknameController nController = new NicknameController(this);
		nController.initialize();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(com.lyra.eartrainer.R.menu.main, menu);
		return true;
	}

}
