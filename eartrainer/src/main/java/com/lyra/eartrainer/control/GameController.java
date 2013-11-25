package com.lyra.eartrainer.control;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.View;
import android.widget.ImageButton;

import com.lyra.eartrainer.GameActivity;
import com.lyra.eartrainer.PauseActivity;
import com.lyra.eartrainer.R;
import com.lyra.eartrainer.model.GamePlay;
import com.lyra.eartrainer.view.GameInterface;

public class GameController extends Controller {
	private GameInterface gameView;
	private GamePlay game;
	private int currentNote;

	public GameController(GameActivity gameActivity) {
		super(gameActivity);
	}

	public void initialize() {
		
		activity.setContentView(R.layout.piano);
		activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		
		game = GamePlay.instance();
		gameView = new GameInterface(activity,game);
        
		attachEvents();
	}
	
	private void attachEvents(){
		//adding submit button click handler
		ImageButton replay = (ImageButton) activity.findViewById(R.id.replay_button);
        ImageButton pause = (ImageButton) activity.findViewById(R.id.pause_button);

        ImageButton[] keys = new ImageButton[13];
        
       	keys[0] = (ImageButton) activity.findViewById(R.id.key1);
        keys[1] = (ImageButton) activity.findViewById(R.id.key2);
        keys[2] = (ImageButton) activity.findViewById(R.id.key3);
        keys[3] = (ImageButton) activity.findViewById(R.id.key4);
        keys[4] = (ImageButton) activity.findViewById(R.id.key5);
        keys[5] = (ImageButton) activity.findViewById(R.id.key6);
        keys[6] = (ImageButton) activity.findViewById(R.id.key7);
        keys[7] = (ImageButton) activity.findViewById(R.id.key8);
        keys[8] = (ImageButton) activity.findViewById(R.id.key9);
        keys[9] = (ImageButton) activity.findViewById(R.id.key10);
        keys[10] = (ImageButton) activity.findViewById(R.id.key11);
        keys[11] = (ImageButton) activity.findViewById(R.id.key12);
        keys[12] = (ImageButton) activity.findViewById(R.id.key13);
        
        replay.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				replayNote(currentNote);
				int oldScore = game.getScore();
				game.setScore(++oldScore);
				gameView.updateScore();
			}
		});
        
        pause.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
				goToPause();
			}
		});
        
        for(int i = 0; i < keys.length; i++ )
        {
        	
        	final int note = i;
        	
        	keys[i].setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					game.getInstrument().playNote(note);
					int oldScore = game.getScore();
					game.setScore(++oldScore);
					gameView.updateScore();
				}
			});
        }
	}
	
	public void replayNote(int note) {
		// TODO Auto-generated method stub
		game.getInstrument().playNote(note);
		//		Toast.makeText(activity, note, Toast.LENGTH_SHORT).show();
	}

	public void goToPause(){
		Intent intent = new Intent(activity,PauseActivity.class);
		activity.startActivity(intent);
	}
}
