package com.lyra.eartrainer.control;

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
import com.lyra.eartrainer.model.GamePlay;
import com.lyra.eartrainer.model.factory.LyraAbstractFactory;
import com.lyra.eartrainer.model.factory.LyraFactoryCreator;
import com.lyra.eartrainer.model.globals.Difficulties;
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
		// TODO change settings in gameplay instance
    	game.setDifficulty(Difficulties.BEGINNER);
    	game.setMode(Modes.PRACTICE);
    	LyraAbstractFactory factory = LyraFactoryCreator.getFactory(ScaleTypes.MAJOR);
    	MusicInfo mi = makeUserInfo();
    	IMusicInstrument piano = factory.createInstrument(mi);
    	game.setInstrument(piano);
    }
    
    private MusicInfo makeUserInfo(){
		SoundPool sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);

        int[] notes = new int[13];
        notes[0] = sp.load(activity, R.raw.p040, 1);
        notes[1] = sp.load(activity, R.raw.p041, 1);
        notes[2] = sp.load(activity, R.raw.p042, 1);
        notes[3] = sp.load(activity, R.raw.p043, 1);
        notes[4] = sp.load(activity, R.raw.p044, 1);
        notes[5] = sp.load(activity, R.raw.p045, 1);
        notes[6] = sp.load(activity, R.raw.p046, 1);
        notes[7] = sp.load(activity, R.raw.p047, 1);
        notes[8] = sp.load(activity, R.raw.p048, 1);
        notes[9] = sp.load(activity, R.raw.p049, 1);
        notes[10] = sp.load(activity, R.raw.p050, 1);
        notes[11] = sp.load(activity, R.raw.p051, 1);
        notes[12] = sp.load(activity, R.raw.p052, 1);
        
        MusicInfo mi = new MusicInfo();
        mi.setSoundNotes(notes);
        mi.setSoundPool(sp);
        
        return mi;
    }
}
