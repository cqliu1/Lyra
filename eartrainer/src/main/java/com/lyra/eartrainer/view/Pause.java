package com.lyra.eartrainer.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.lyra.eartrainer.PauseActivity;

public class Pause extends View{
	
	
	public Pause(Context con, AttributeSet attrs){
		super(con, attrs);
		
	}
	
	public void updateVolume(int newVolume)
	{
		PauseActivity pause = (PauseActivity) getContext();
		pause.changeVolume(newVolume);
	}
	
	public void resumeGameplay()
	{
		PauseActivity pause = (PauseActivity) getContext();
		pause.goToGame();
	}
	
	public void startNewGame()
	{
		PauseActivity pause = (PauseActivity) getContext();
		pause.goToOptions();
	}

	public void showGameOver() {
		// TODO Auto-generated method stub
		PauseActivity pause = (PauseActivity) getContext();
		pause.goToGameOver();
	}
}
