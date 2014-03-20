package com.lyra.eartrainer.control;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.lyra.eartrainer.GameActivity;
import com.lyra.eartrainer.GameOverActivity;
import com.lyra.eartrainer.OptionsActivity;
import com.lyra.eartrainer.PauseActivity;
import com.lyra.eartrainer.R;
import com.lyra.eartrainer.model.GamePlay;
import com.lyra.eartrainer.view.Pause;

public class PauseController extends Controller {
	private Pause pView;
	private GamePlay game;
	public PauseController(PauseActivity pActivity){
		super(pActivity);
	}
	
	public void initialize(){
		//creating the view
		activity.setContentView(R.layout.activity_pause);
		activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		pView = new Pause(activity);
		game = GamePlay.instance();
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
		volumeBar.setMax(100);
		float vol = game.getVolume()*100;
		volumeBar.setProgress((int)vol);
		volumeBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
		{
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) 
			{
				// TODO Auto-generated method stub
				
			}
			public void onStartTrackingTouch(SeekBar seekBar) 
			{
				// TODO Auto-generated method stub
				
			}
			public void onStopTrackingTouch(SeekBar seekBar) 
			{
				// TODO Auto-generated method stub
				updateVolume(seekBar.getProgress());
				
			}
		});
	}
	
	public void resumeGame(){
		activity.finish();
	}

	public void restartGame(){
		Intent intent = new Intent(activity,OptionsActivity.class);
		activity.startActivity(intent); 
		activity.setResult(GameActivity.GAME_CANCELLED);
		//this.getParent().finish();
		activity.finish();
	}

	public void quitGame() {
		Intent intent = new Intent(activity,GameOverActivity.class);
		activity.startActivity(intent); 
		activity.setResult(GameActivity.GAME_CANCELLED);
		//this.getParent().finish();
		activity.finish();
	}
	
	public void updateVolume(int vol){
		game.setVolume((float)vol/100);
		game.getInstrument().playNote(0);
	}
}
