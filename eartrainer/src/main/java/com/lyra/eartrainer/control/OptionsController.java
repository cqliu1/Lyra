package com.lyra.eartrainer.control;

import java.lang.reflect.Field;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.lyra.eartrainer.GameActivity;
import com.lyra.eartrainer.OptionsActivity;
import com.lyra.eartrainer.R;
import com.lyra.eartrainer.R.raw;
import com.lyra.eartrainer.model.GamePlay;
import com.lyra.eartrainer.model.factory.LyraAbstractFactory;
import com.lyra.eartrainer.model.factory.LyraFactoryCreator;
import com.lyra.eartrainer.model.globals.Difficulties;
import com.lyra.eartrainer.model.globals.InstrumentTypes;
import com.lyra.eartrainer.model.globals.Modes;
import com.lyra.eartrainer.model.globals.ScaleTypes;
import com.lyra.eartrainer.model.instrument.IMusicInstrument;
import com.lyra.eartrainer.model.instrument.MusicInfo;
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
    	
    	if(((String)instrument.getSelectedItem()).equals("Piano")) {
    		game.setInstrumentType(InstrumentTypes.PIANO);
    	}
    	
		// TODO change settings in gameplay instance
    	game.setDifficulty(Difficulties.BEGINNER);
    	game.setMode(Modes.PRACTICE);
    	LyraAbstractFactory factory = LyraFactoryCreator.getFactory(ScaleTypes.MAJOR);
    	MusicInfo mi = makeMusicInfo();
    	IMusicInstrument piano = factory.createInstrument(mi);
    	game.setInstrument(piano);
    }
    
    private MusicInfo makeMusicInfo(){
		SoundPool sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        
        MusicInfo mi = new MusicInfo();
        
        int[] notes = null;
        
        // For piano instrument type load the piano sounds
        if(game.getInstrumentType() == InstrumentTypes.PIANO) {
        	Class<raw> raw = R.raw.class;
        	Field[] fields = raw.getFields();
        	notes = new int[fields.length];
        	for(int i=0; i<fields.length; i++) {
        		Field f = fields[i];
        		if(f.getName().startsWith("p")) {
        			try {
						notes[i] = sp.load(activity, f.getInt(null), 1);
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
        		}
        	}
        }
        
        mi.setSoundNotes(notes);
        mi.setSoundPool(sp);
        
        return mi;
    }
}
