package com.lyra.eartrainer.view;

import com.lyra.eartrainer.R;
import com.lyra.eartrainer.model.GamePlay;
import com.lyra.eartrainer.model.globals.InstrumentTypes;
import com.lyra.eartrainer.model.instrument.Piano;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class GameInterface extends View {
	
//	private ImageButton replay;
//	private ImageButton pause;
	private TextView score;
	private ImageButton[] keys;
	private GamePlay gameplay;
	
//	SoundPool sp;
//	int[] notes;
//	int currentNote;
	
    public GameInterface(Activity activity, GamePlay game) {
    	super(activity);
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
	
	public void selectCorrectNote(int note) {
		if(GamePlay.instance().getInstrumentType() == InstrumentTypes.PIANO) {
			Piano piano = (Piano) GamePlay.instance().getInstrument();
			
			if(piano.isBlackKey(note)) {
				keys[note].setImageResource(R.drawable.correct_black_key);
			} else {
				keys[note].setImageResource(R.drawable.correct_white_key);
			}	
		}
	}
	
	public void selectIncorrectNote(int note) {
		if(GamePlay.instance().getInstrumentType() == InstrumentTypes.PIANO) {
			Piano piano = (Piano) GamePlay.instance().getInstrument();
			
			if(piano.isBlackKey(note)) {
				keys[note].setImageResource(R.drawable.wrong_black_key);
			} else {
				keys[note].setImageResource(R.drawable.wrong_white_key);
			}	
		}
	}
	
	public void resetNote(int note) {
		if(GamePlay.instance().getInstrumentType() == InstrumentTypes.PIANO) {
			Piano piano = (Piano) GamePlay.instance().getInstrument();
			
			if(piano.isBlackKey(note)) {
				keys[note].setImageResource(R.drawable.black_key_selection);
			} else {
				keys[note].setImageResource(R.drawable.white_key_selection);
			}	
		}
	}
	
	public void selectNote(int note) {
		if(GamePlay.instance().getInstrumentType() == InstrumentTypes.PIANO) {
			Piano piano = (Piano) GamePlay.instance().getInstrument();
			
			if(piano.isBlackKey(note)) {
				keys[note].setImageResource(R.drawable.black_key_select);
			} else {
				keys[note].setImageResource(R.drawable.white_key_select);
			}	
		}
	}
	
}
