package com.lyra.eartrainer.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.view.View;

import com.lyra.eartrainer.PauseActivity;

public class Pause extends View{
	
	Context context = getContext();
	SharedPreferences sharedPref = context.getSharedPreferences("@string/volume_val", Context.MODE_PRIVATE);
	SharedPreferences.Editor editor = sharedPref.edit();
	public Pause(Context con, AttributeSet attrs){
		super(con, attrs);
		
	}
	
	public void updateVolume(int newVolume)
	{
		//PauseActivity pause = (PauseActivity) getContext();
		//pause.changeVolume(newVolume);
		editor.putInt("@string/volume_val", newVolume);
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
