package com.lyra.eartrainer.view;

import com.lyra.eartrainer.R;
import com.lyra.eartrainer.model.GamePlay;
import com.lyra.eartrainer.model.globals.InstrumentTypes;
import com.lyra.eartrainer.model.globals.Modes;
import com.lyra.eartrainer.model.instrument.Piano;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class GameInterface extends View {
	
//	private ImageButton replay;
//	private ImageButton pause;
	private TextView score;
	private TextView strikes;
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
        strikes = (TextView) activity.findViewById(R.id.strikesText);
        if (GamePlay.instance().getMode() != Modes.FREEPLAY) score.setText(""+gameplay.getScore());
        else score.setText("");
        if (GamePlay.instance().getMode() == Modes.CHALLENGE) strikes.setText("Strikes: " + gameplay.getStrikes());
        else strikes.setText("");
        
        if (GamePlay.instance().getInstrumentType() == InstrumentTypes.PIANO)
        {
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
        else if (GamePlay.instance().getInstrumentType() == InstrumentTypes.GUITAR)
        {
        	// TODO: add guitar buttons to keys
        }
	}

	public void updateScore() {
		// TODO Auto-generated method stub
		if (GamePlay.instance().getMode() != Modes.FREEPLAY) score.setText("" + gameplay.getScore());
	}
	
	public void updateStrikes()
	{
		 if (GamePlay.instance().getMode() == Modes.CHALLENGE) strikes.setText("Strikes: " + gameplay.getStrikes());
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
		else if (GamePlay.instance().getInstrumentType() == InstrumentTypes.GUITAR) {
			// TODO: correct guitar notes
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
		else if (GamePlay.instance().getInstrumentType() == InstrumentTypes.GUITAR){
			// TODO: incorrect guitar notes
		}
		if (GamePlay.instance().getMode() == Modes.CHALLENGE) strikes.setText("Strikes: " + gameplay.getStrikes());
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
		else if (GamePlay.instance().getInstrumentType() == InstrumentTypes.GUITAR) {
			// TODO: resetting guitar notes
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
		else if (GamePlay.instance().getInstrumentType() == InstrumentTypes.GUITAR) {
			// TODO: select guitar notes
		}
	}
}
