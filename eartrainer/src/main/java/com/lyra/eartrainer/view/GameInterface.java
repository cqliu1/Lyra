package com.lyra.eartrainer.view;

import com.lyra.eartrainer.GameActivity;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

public class GameInterface extends View{
    public GameInterface(Context con, AttributeSet attrs) {
    	super(con, attrs);
	}
    
    public void playNote(String note) {
		// TODO Auto-generated method stub
    	GameActivity game = (GameActivity) getContext();
		game.setScore(0);
		Toast.makeText(game, note, Toast.LENGTH_SHORT).show();
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
