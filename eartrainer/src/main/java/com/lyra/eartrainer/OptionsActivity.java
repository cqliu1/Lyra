package com.lyra.eartrainer;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.Menu;

import com.lyra.eartrainer.control.OptionsController;

public class OptionsActivity extends Activity {

    /**
     * Called when the activity is first created.
     * @param savedInstanceState If the activity is being re-initialized after 
     * previously being shut down then this Bundle contains the data it most 
     * recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
        OptionsController optionsControl = new OptionsController(this);
        optionsControl.initialize();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(com.lyra.eartrainer.R.menu.main, menu);
		return true;
    }
}

