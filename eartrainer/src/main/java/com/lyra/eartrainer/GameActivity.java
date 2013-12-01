package com.lyra.eartrainer;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.lyra.eartrainer.control.GameController;

public class GameActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		GameController  gameControl = new GameController(this);
		gameControl.initialize();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(com.lyra.eartrainer.R.menu.main, menu);
		return true;
	}
}
