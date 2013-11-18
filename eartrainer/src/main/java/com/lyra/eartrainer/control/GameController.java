package com.lyra.eartrainer.control;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.lyra.eartrainer.GameActivity;
import com.lyra.eartrainer.R;
import com.lyra.eartrainer.view.GameInterface;

public class GameController extends Controller {
	private GameInterface gameView;
	private Context con;
	private String note;

	public GameController(GameActivity gameActivity) {
		super(gameActivity);
	}

	public void initialize() {
		gameView = new GameInterface(activity,null);
		attachEvents();
	}
	
	private void attachEvents(){
		//adding submit button click handler
		ImageButton replay = (ImageButton) activity.findViewById(R.id.replay_button);
        ImageButton pause = (ImageButton) activity.findViewById(R.id.pause_button);
        TextView score = (TextView) activity.findViewById(R.id.score);
        score.setText("@string/score");

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
				gameView.replayNotes(con,note);
			}
		});
        
        pause.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
				gameView.showPauseMenu();
			}
		});
        
        for(int i = 0; i < keys.length; i++ )
        {
        	keys[i].setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					note = (String) v.getContentDescription();
					gameView.playNote(note);
				}
			});
        }
	}
}
