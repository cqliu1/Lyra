package com.lyra.eartrainer;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageButton;
import android.widget.TextView;

import com.lyra.eartrainer.control.GameController;

public class GameActivity extends Activity {
	
	private ImageButton replay;
	private ImageButton pause;
	private TextView score;
	private int scoreNum;
	private ImageButton[] keys;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.piano);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		GameController  gameControl = new GameController(this);
		gameControl.initialize();
		
    	replay = (ImageButton) this.findViewById(R.id.replay_button);
        pause = (ImageButton) this.findViewById(R.id.pause_button);
        score = (TextView) this.findViewById(R.id.score);
        scoreNum = 0;
        score.setText(""+scoreNum);

        keys = new ImageButton[13];
        
       	keys[0] = (ImageButton) findViewById(R.id.key1);
        keys[1] = (ImageButton) findViewById(R.id.key2);
        keys[2] = (ImageButton) findViewById(R.id.key3);
        keys[3] = (ImageButton) findViewById(R.id.key4);
        keys[4] = (ImageButton) findViewById(R.id.key5);
        keys[5] = (ImageButton) findViewById(R.id.key6);
        keys[6] = (ImageButton) findViewById(R.id.key7);
        keys[7] = (ImageButton) findViewById(R.id.key8);
        keys[8] = (ImageButton) findViewById(R.id.key9);
        keys[9] = (ImageButton) findViewById(R.id.key10);
        keys[10] = (ImageButton) findViewById(R.id.key11);
        keys[11] = (ImageButton) findViewById(R.id.key12);
        keys[12] = (ImageButton) findViewById(R.id.key13);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(com.lyra.eartrainer.R.menu.main, menu);
		return true;
	}
	
	public void goToPause(){
		Intent intent = new Intent(this,PauseActivity.class);
		startActivity(intent);
	}
	
	public void setScore(int num)
	{
		//this.scoreNum = num;
		scoreNum++;
		score.setText(""+scoreNum);
	}
}
