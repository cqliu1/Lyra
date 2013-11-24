package com.lyra.eartrainer.control;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.lyra.eartrainer.GameActivity;
import com.lyra.eartrainer.OptionsActivity;
import com.lyra.eartrainer.R;
import com.lyra.eartrainer.factory.abs.LyraAbstractFactory;
import com.lyra.eartrainer.factory.abs.LyraFactoryCreator;
import com.lyra.eartrainer.model.GamePlay;
import com.lyra.eartrainer.model.globals.Difficulties;
import com.lyra.eartrainer.model.globals.Modes;
import com.lyra.eartrainer.model.globals.ScaleTypes;
import com.lyra.eartrainer.model.instrument.IMusicInstrument;
import com.lyra.eartrainer.view.Options;

public class OptionsController extends Controller {
	private Options optionsView;
	private Spinner gameMode;
	private Spinner difficulty;
	private Spinner scale;
	private Spinner instrument;
	private GamePlay game;
	
	public OptionsController(OptionsActivity optionsActivity) {
		super(optionsActivity);
	}

	public void initialize() {
        activity.setContentView(R.layout.options);
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        
		optionsView = new Options(activity);
		game = GamePlay.instance();
		gameMode = (Spinner)activity.findViewById(R.id.gameModeSpinner);
		difficulty = (Spinner)activity.findViewById(R.id.difficultySpinner);
		attachEvents();
		scale = (Spinner)activity.findViewById(R.id.scaleSpinner);
		instrument = (Spinner)activity.findViewById(R.id.instrumentSpinner);
	}
	
	private void attachEvents(){
		//adding button click handlers
		Button start = (Button)activity.findViewById(R.id.startGame);
		start.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				storeOptions();
				goToGame();
			}
		});
	}
    
    public void goToGame(){
    	Intent intent = new Intent(activity,GameActivity.class);
    	activity.startActivity(intent);
    	activity.finish();
    }
    
    public void storeOptions(){
		// TODO change settings in gameplay instance
    	game.setDifficulty(Difficulties.BEGINNER);
    	game.setMode(Modes.PRACTICE);
    	LyraAbstractFactory factory = LyraFactoryCreator.getFactory(ScaleTypes.MAJOR);
    	IMusicInstrument piano = factory.createInstrument(activity);
    	game.setInstrument(piano);
    }
}
