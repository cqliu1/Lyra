package com.lyra.eartrainer.view;

import android.app.Activity;
import android.widget.ImageButton;
import android.widget.TextView;

import com.lyra.eartrainer.R;
import com.lyra.eartrainer.control.LyraView;
import com.lyra.eartrainer.model.GamePlay;
import com.lyra.eartrainer.model.Round;
import com.lyra.eartrainer.model.globals.InstrumentTypes;
import com.lyra.eartrainer.model.globals.Modes;
import com.lyra.eartrainer.model.instrument.Piano;

public class GameInterface extends LyraView {
	
//	private ImageButton replay;
//	private ImageButton pause;
	private TextView score;
	private TextView strikes;
	private ImageButton[] keys;
	private GamePlay gameplay;
	
//	SoundPool sp;
//	int[] notes;
//	int currentNote;
	
    public GameInterface(Activity act, GamePlay game) {
    	super(act);
//    	replay = (ImageButton) activity.findViewById(R.id.replay_button);
//      pause = (ImageButton) activity.findViewById(R.id.pause_button);
    	gameplay = game;
        score = (TextView) activity.findViewById(R.id.score);
        strikes = (TextView) activity.findViewById(R.id.strikesText);
        if (GamePlay.instance().getMode() != Modes.FREEPLAY ) score.setText(""+gameplay.getScore());
        else score.setText("");
        if (GamePlay.instance().getMode() == Modes.CHALLENGE) strikes.setText("Strikes: " + gameplay.getStrikes());
        else strikes.setText("");
        
        if (GamePlay.instance().getInstrumentType() == InstrumentTypes.PIANO)
        {
	        keys = new ImageButton[25];
	        
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
	        keys[13] = (ImageButton) activity.findViewById(R.id.key14);
	        keys[14] = (ImageButton) activity.findViewById(R.id.key15);
	        keys[15] = (ImageButton) activity.findViewById(R.id.key16);
	        keys[16] = (ImageButton) activity.findViewById(R.id.key17);
	        keys[17] = (ImageButton) activity.findViewById(R.id.key18);
	        keys[18] = (ImageButton) activity.findViewById(R.id.key19);
	        keys[19] = (ImageButton) activity.findViewById(R.id.key20);
	        keys[20] = (ImageButton) activity.findViewById(R.id.key21);
	        keys[21] = (ImageButton) activity.findViewById(R.id.key22);
	        keys[22] = (ImageButton) activity.findViewById(R.id.key23);
	        keys[23] = (ImageButton) activity.findViewById(R.id.key24);
	        keys[24] = (ImageButton) activity.findViewById(R.id.key25);
        }
        else if (GamePlay.instance().getInstrumentType() == InstrumentTypes.GUITAR)
        {
        	// TODO: add guitar buttons to keys
        	keys = new ImageButton[36];
        	
        	keys[0] = (ImageButton) activity.findViewById(R.id.tab1);
        	keys[1] = (ImageButton) activity.findViewById(R.id.tab2);
        	keys[2] = (ImageButton) activity.findViewById(R.id.tab3);
        	keys[3] = (ImageButton) activity.findViewById(R.id.tab4);
        	keys[4] = (ImageButton) activity.findViewById(R.id.tab5);
        	keys[5] = (ImageButton) activity.findViewById(R.id.tab6);
        	keys[6] = (ImageButton) activity.findViewById(R.id.tab7);
        	keys[7] = (ImageButton) activity.findViewById(R.id.tab8);
        	keys[8] = (ImageButton) activity.findViewById(R.id.tab9);
        	keys[9] = (ImageButton) activity.findViewById(R.id.tab10);
        	keys[10] = (ImageButton) activity.findViewById(R.id.tab11);
        	keys[11] = (ImageButton) activity.findViewById(R.id.tab12);
        	keys[12] = (ImageButton) activity.findViewById(R.id.tab13);
        	keys[13] = (ImageButton) activity.findViewById(R.id.tab14);
        	keys[14] = (ImageButton) activity.findViewById(R.id.tab15);
        	keys[15] = (ImageButton) activity.findViewById(R.id.tab16);
        	keys[16] = (ImageButton) activity.findViewById(R.id.tab17);
        	keys[17] = (ImageButton) activity.findViewById(R.id.tab18);
        	keys[18] = (ImageButton) activity.findViewById(R.id.tab19);
        	keys[19] = (ImageButton) activity.findViewById(R.id.tab20);
        	keys[20] = (ImageButton) activity.findViewById(R.id.tab21);
        	keys[21] = (ImageButton) activity.findViewById(R.id.tab22);
        	keys[22] = (ImageButton) activity.findViewById(R.id.tab23);
        	keys[23] = (ImageButton) activity.findViewById(R.id.tab24);
        	keys[24] = (ImageButton) activity.findViewById(R.id.tab25);
        	keys[25] = (ImageButton) activity.findViewById(R.id.tab26);
        	keys[26] = (ImageButton) activity.findViewById(R.id.tab27);
        	keys[27] = (ImageButton) activity.findViewById(R.id.tab28);
        	keys[28] = (ImageButton) activity.findViewById(R.id.tab29);
        	keys[29] = (ImageButton) activity.findViewById(R.id.tab30);
        	keys[30] = (ImageButton) activity.findViewById(R.id.tab31);
        	keys[31] = (ImageButton) activity.findViewById(R.id.tab32);
        	keys[32] = (ImageButton) activity.findViewById(R.id.tab33);
        	keys[33] = (ImageButton) activity.findViewById(R.id.tab34);
        	keys[34] = (ImageButton) activity.findViewById(R.id.tab35);
        	keys[35] = (ImageButton) activity.findViewById(R.id.tab36);
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
			keys[note].setImageResource(R.drawable.correct_tab);
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
			keys[note].setImageResource(R.drawable.wrong_tab);
		}
		if (GamePlay.instance().getMode() == Modes.CHALLENGE) strikes.setText("Strikes: " + gameplay.getStrikes());
	}
	
	public void resetNote(int note) {
		Round round = GamePlay.instance().getCurrentRound();
		
		if(GamePlay.instance().getInstrumentType() == InstrumentTypes.PIANO) {
			Piano piano = (Piano) GamePlay.instance().getInstrument();
//			
//			if(piano.isBlackKey(note)) {
//				if(round != null && !round.getFinished() && round.getFirstNote() == note) // if first note played, keep highlighted
//					keys[note].setImageResource(R.drawable.correct_black_key);
//				else
//					keys[note].setImageResource(R.drawable.black_key_selection);
//			} else {
//				if(round != null && !round.getFinished() && round.getFirstNote() == note)
//					keys[note].setImageResource(R.drawable.correct_white_key);
//				else
//				keys[note].setImageResource(R.drawable.white_key_selection);
//			}	
			
			if(round != null && !round.getFinished() && round.getFirstNote() == note){
				if(piano.isBlackKey(note)) {
					keys[note].setImageResource(R.drawable.correct_black_key);
				} else
					keys[note].setImageResource(R.drawable.correct_white_key);
			} else {
				switch(note){
				case 0:
					keys[note].setImageResource(R.drawable.c3_key);
					break;
				case 1:
				case 13:
					keys[note].setImageResource(R.drawable.cd_key);
					break;
				case 2:
				case 14:
					keys[note].setImageResource(R.drawable.d_key);
					break;
				case 3:
				case 15:
					keys[note].setImageResource(R.drawable.de_key);
					break;
				case 4:
				case 16:
					keys[note].setImageResource(R.drawable.e_key);
					break;
				case 5:
				case 17:
					keys[note].setImageResource(R.drawable.f_key);
					break;
				case 6:
				case 18:
					keys[note].setImageResource(R.drawable.fg_key);
					break;
				case 7:
				case 19:
					keys[note].setImageResource(R.drawable.g_key);
					break;
				case 8:
				case 20:
					keys[note].setImageResource(R.drawable.ga_key);
					break;
				case 9:
				case 21:
					keys[note].setImageResource(R.drawable.a_key);
					break;
				case 10:
				case 22:
					keys[note].setImageResource(R.drawable.ab_key);
					break;
				case 11:
				case 23:
					keys[note].setImageResource(R.drawable.b_key);
					break;
				case 12:
					keys[note].setImageResource(R.drawable.c4_key);
					break;
				case 24:
					keys[note].setImageResource(R.drawable.c5_key);
					break;
				}
			}
		}
		else if (GamePlay.instance().getInstrumentType() == InstrumentTypes.GUITAR) {
//			Guitar guitar = (Guitar) GamePlay.instance().getInstrument();
			if(round != null && !round.getFinished() && round.getFirstNote() == note)
				keys[note].setImageResource(R.drawable.correct_tab);
			else{
				switch(note){
					case 0: //tab 1
						keys[note].setImageResource(R.drawable.e_tab); // reset open e tab 
						break;
					case 6: //tab 7
						keys[note].setImageResource(R.drawable.a_tab); // reset open a tab
						break;
					case 12: //tab 13
						keys[note].setImageResource(R.drawable.d_tab); // reset open d tab
						break;
					case 18: //tab 19
						keys[note].setImageResource(R.drawable.g_tab); // reset open g tab
						break;
					case 24: //tab 25 
						keys[note].setImageResource(R.drawable.b_tab); // reset open b tab
						break;
					case 30: //tab 31
						keys[note].setImageResource(R.drawable.e_tab); // reset open e tab
						break;
					default: 
						keys[note].setImageResource(R.drawable.blank_tab); //reset rest to blank tab
				}
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
		else if (GamePlay.instance().getInstrumentType() == InstrumentTypes.GUITAR) {
			// TODO: select guitar notes
			keys[note].setImageResource(R.drawable.silver_tab);
		}
	}
}
