package com.lyra.eartrainer;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.SeekBar;

import com.lyra.eartrainer.control.PauseController;

public class PauseActivity extends Activity {
	private Button resume;
	private Button restart;
	private Button quit;
	private SeekBar volumeBar;
	private int volume = 50;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pause);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		PauseController pController = new PauseController(this);
		pController.initialize();
		
		this.resume = (Button) findViewById(R.id.btnResumeGame);
		this.restart = (Button) findViewById(R.id.btnRestartGame);
		this.quit = (Button) findViewById(R.id.btnQuitGame);
		this.volumeBar = (SeekBar) findViewById(R.id.seekVolume);
		volumeBar.scrollTo(volume, 1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(com.lyra.eartrainer.R.menu.main, menu);
		return true;
	}

	public void goToGame(){
		finish();
	}
	
	public void goToOptions(){
		Intent intent = new Intent(this,OptionsActivity.class);
		startActivity(intent);

		this.getParent().finish();
		finish();
	}

	public void goToGameOver() {
		Intent intent = new Intent(this,GameOverActivity.class);
		startActivity(intent);
		
		this.getParent().finish();
		finish();
	}
	
	public void changeVolume(int newVolume){
		volume = newVolume;
		volumeBar.scrollTo(volume, 1);
	}
}
