package com.lyra.eartrainer.view;

import com.lyra.eartrainer.GameActivity;
import com.lyra.eartrainer.R;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;
import android.content.SharedPreferences;

public class GameInterface extends View{
	
	SoundPool sp;
	int[] notes;
	Context context = getContext();
	//SharedPreferences sharedPref = context.getSharedPreferences("volume_val", Context.MODE_PRIVATE);
	//int newVolume = getResources().getInteger(R.string.volume_val);
	
    public GameInterface(Context con, AttributeSet attrs) {
    	super(con, attrs);
    	
    	sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);


    	notes = new int[13];
    	notes[0] = sp.load(con, R.raw.p040, 1); 
    	notes[1] = sp.load(con, R.raw.p041, 1); 
    	notes[2] = sp.load(con, R.raw.p042, 1); 
    	notes[3] = sp.load(con, R.raw.p043, 1); 
    	notes[4] = sp.load(con, R.raw.p044, 1); 
    	notes[5] = sp.load(con, R.raw.p045, 1); 
    	notes[6] = sp.load(con, R.raw.p046, 1); 
    	notes[7] = sp.load(con, R.raw.p047, 1); 
    	notes[8] = sp.load(con, R.raw.p048, 1); 
    	notes[9] = sp.load(con, R.raw.p049, 1); 
    	notes[10] = sp.load(con, R.raw.p050, 1); 
    	notes[11] = sp.load(con, R.raw.p051, 1); 
    	notes[12] = sp.load(con, R.raw.p052, 1);
    	
	}
    
    public void playNote(int note) {
		// TODO Auto-generated method stub
    	GameActivity game = (GameActivity) getContext();
		game.setScore(0);
		//Toast.makeText(game, note, Toast.LENGTH_SHORT).show();
		
		// this is where we set the volume
		//float vol = (float)newVolume/100;
		sp.play(notes[note], 1.0f, 1.0f, 0, 0, 1.0f);
		
		
	}
	
	public void replayNotes(Context con, String note) {
		// TODO Auto-generated method stub
		GameActivity game = (GameActivity) getContext();
		game.setScore(0);
		Toast.makeText(game, note, Toast.LENGTH_SHORT).show();
	}

	public void showPauseMenu() {
		GameActivity game = (GameActivity) getContext();
		game.goToPause();
	}
}
