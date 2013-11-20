package com.lyra.eartrainer.control;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.DragEvent;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.lyra.eartrainer.PauseActivity;
import com.lyra.eartrainer.R;
import com.lyra.eartrainer.view.Pause;

public class PauseController extends Controller {
	private Pause pView;
	public PauseController(PauseActivity pActivity){
		super(pActivity);
	}
	
	public void initialize(){
		//creating the view
		pView = new Pause(activity,null);
		//attaching event listeners to view widgets
		attachEvents();
	}

	private void attachEvents(){
		// resume
		Button btnResume = (Button)activity.findViewById(R.id.btnResumeGame);
		btnResume.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				resumeGame();
			}
		});
		// restart
		Button btnRestart = (Button)activity.findViewById(R.id.btnRestartGame);
		btnRestart.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				restartGame();
			}
		});
		// quit
		Button btnQuit = (Button)activity.findViewById(R.id.btnQuitGame);
		btnQuit.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				quitGame();
			}
		});
		// adjust volume
		SeekBar volumeBar = (SeekBar)activity.findViewById(R.id.seekVolume);
		volumeBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
		{
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) 
			{
				// TODO Auto-generated method stub
				
				pView.updateVolume(seekBar.getProgress());
			}
			public void onStartTrackingTouch(SeekBar seekBar) 
			{
				// TODO Auto-generated method stub
				
			}
			public void onStopTrackingTouch(SeekBar seekBar) 
			{
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private void resumeGame()
	{
		// resume the game!
		pView.resumeGameplay();
	}
	private void restartGame()
	{
		// restart the game!
		pView.startNewGame();
	}
	private void quitGame()
	{
		// record our score?
		// exit
		pView.showGameOver();
	}
}
