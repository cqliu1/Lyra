package com.lyra.eartrainer.view;

import com.lyra.eartrainer.R;
import com.lyra.eartrainer.model.GamePlay;

import android.app.Activity;
import android.widget.ImageButton;
import android.widget.TextView;

public class GameInterface{
	
//	private ImageButton replay;
//	private ImageButton pause;
	private TextView score;
	private ImageButton[] keys;
	private GamePlay gameplay;
	
//	SoundPool sp;
//	int[] notes;
//	int currentNote;
	
    public GameInterface(Activity activity, GamePlay game) {
//    	replay = (ImageButton) activity.findViewById(R.id.replay_button);
//      pause = (ImageButton) activity.findViewById(R.id.pause_button);
    	gameplay = game;
        score = (TextView) activity.findViewById(R.id.score);
        score.setText(""+gameplay.getScore());

        keys = new ImageButton[13];
        
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
    	
	}

	public void updateScore() {
		// TODO Auto-generated method stub
		score.setText("" + gameplay.getScore());
	}
	
	
}
