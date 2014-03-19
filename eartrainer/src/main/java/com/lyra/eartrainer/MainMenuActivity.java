package com.lyra.eartrainer;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.Menu;
import android.media.MediaPlayer;
import android.widget.MediaController;

import com.lyra.eartrainer.control.MainMenuController;

public class MainMenuActivity extends Activity {

		MediaPlayer mPlayer;
        @Override
        public void onCreate(Bundle savedInstanceBundle) {
                super.onCreate(savedInstanceBundle);
        		setVolumeControlStream(AudioManager.STREAM_MUSIC);
        		mPlayer = MediaPlayer.create(this, R.raw.tutorial);
                MainMenuController mmController = new MainMenuController(this);
                mmController.initialize();
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
                // Inflate the menu; this adds items to the action bar if it is present.
                getMenuInflater().inflate(com.lyra.eartrainer.R.menu.main, menu);
                return true;
        }
        public MediaPlayer getPlayer()
        {
        	return mPlayer;
        }
}