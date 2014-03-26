package com.lyra.eartrainer;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;

import com.lyra.eartrainer.control.MainMenuController;
import com.lyra.eartrainer.control.TutorialController;

public class TutorialActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceBundle) {
        super.onCreate(savedInstanceBundle);
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		TutorialController tController = new TutorialController(this);
		tController.initialize();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(com.lyra.eartrainer.R.menu.main, menu);
		return true;
	}

}

