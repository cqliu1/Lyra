package com.lyra.eartrainer.control;

import java.lang.reflect.Field;
import java.util.ArrayList;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
//import android.widget.Spinner;


import android.widget.SeekBar;
import android.widget.TextView;

import com.edmodo.rangebar.RangeBar;
import com.lyra.eartrainer.GameActivity;
import com.lyra.eartrainer.OptionsActivity;
import com.lyra.eartrainer.R;
import com.lyra.eartrainer.R.raw;
import com.lyra.eartrainer.model.GamePlay;
import com.lyra.eartrainer.model.globals.Difficulties;
import com.lyra.eartrainer.model.globals.InstrumentTypes;
import com.lyra.eartrainer.model.globals.Modes;
import com.lyra.eartrainer.model.globals.ScaleTypes;
import com.lyra.eartrainer.model.globals.HiLo;
import com.lyra.eartrainer.model.instrument.IMusicInstrument;
import com.lyra.eartrainer.model.instrument.MusicInstrumentFactory;
//import com.lyra.eartrainer.model.globals.ScaleTypes;
//import com.lyra.eartrainer.model.instrument.IMusicInstrument;
import com.lyra.eartrainer.model.instrument.SoundInfo;
import com.lyra.eartrainer.properties.LyraProps;
//import com.lyra.eartrainer.model.instrument.MusicInstrumentFactory;
import com.lyra.eartrainer.view.Options;

public class OptionsController extends Controller {
	private Options optionsView;
	private RadioGroup gameMode;
	// private Spinner difficulty;
	// private Spinner scale;
	private RadioGroup instrument;
	private TextView intervalText;
	private RangeBar interval;
	private TextView noteOrderText;
	private RadioGroup noteOrder;
	private GamePlay game;
	private TextView leftIndexValue;
	private TextView rightIndexValue;

	public OptionsController(OptionsActivity optionsActivity) {
		super(optionsActivity);
	}

	public void initialize() {
		activity.setContentView(R.layout.options);
		activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);

		optionsView = new Options(activity);
		game = GamePlay.instance();
		game.reset();
		gameMode = (RadioGroup) activity.findViewById(R.id.game_mode_group);
		instrument = (RadioGroup) activity.findViewById(R.id.instrument_group);
		intervalText = (TextView) activity.findViewById(R.id.intervalText);
		interval = (RangeBar) activity.findViewById(R.id.intervalBar);
		noteOrderText = (TextView) activity.findViewById(R.id.hi_lo_text);
		noteOrder = (RadioGroup) activity.findViewById(R.id.hi_lo_group);
		leftIndexValue = (TextView) activity.findViewById(R.id.leftInterval);
		rightIndexValue = (TextView) activity.findViewById(R.id.rightInterval);
		
//		difficulty = (Spinner) activity.findViewById(R.id.difficultySpinner);
//		scale = (Spinner) activity.findViewById(R.id.scaleSpinner);
		attachEvents();

		leftIndexValue.setText("" + interval.getLeftIndex());
		rightIndexValue.setText("" + interval.getRightIndex());
        
		if(game.getMode() == Modes.FREEPLAY || game.getMode() == Modes.CHALLENGE){
			noteOrder.setVisibility(View.INVISIBLE);
			noteOrderText.setVisibility(View.INVISIBLE);
			interval.setVisibility(View.INVISIBLE);
			intervalText.setVisibility(View.INVISIBLE);
			leftIndexValue.setVisibility(View.INVISIBLE);
			rightIndexValue.setVisibility(View.INVISIBLE);
		}
	}

	private void attachEvents() {
		// adding button click handlers
		Button start = (Button) activity.findViewById(R.id.startGame);
		
		start.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				storeOptions();
				goToGame();
			}
		});
		
		// Sets the display values of the indices
        interval.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onIndexChangeListener(RangeBar rangeBar, int leftThumbIndex, int rightThumbIndex) {
            	//TODO change this to display interval string, not index
                leftIndexValue.setText("" + leftThumbIndex);
                rightIndexValue.setText("" + rightThumbIndex);
            }
        });
        
		RadioButton freeplay = (RadioButton)activity.findViewById(R.id.freeplay_option);
		freeplay.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
//				RadioButton hi_lo = (RadioButton)activity.findViewById(R.id.hi_lo);
//		    	RadioButton lo_hi = (RadioButton)activity.findViewById(R.id.lo_hi);
//		    	TextView lo_hi_text = (TextView)activity.findViewById(R.id.hi_lo_text);
//				hi_lo.setVisibility(View.INVISIBLE);
//	    		lo_hi.setVisibility(View.INVISIBLE);
//	    		lo_hi_text.setVisibility(View.INVISIBLE);
				
				noteOrder.setVisibility(View.INVISIBLE);
				noteOrderText.setVisibility(View.INVISIBLE);
				interval.setVisibility(View.INVISIBLE);
				intervalText.setVisibility(View.INVISIBLE);
				leftIndexValue.setVisibility(View.INVISIBLE);
				rightIndexValue.setVisibility(View.INVISIBLE);
			}
		});
		
		RadioButton practice = (RadioButton)activity.findViewById(R.id.practice_option);
		practice.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
//				RadioButton hi_lo = (RadioButton)activity.findViewById(R.id.hi_lo);
//		    	RadioButton lo_hi = (RadioButton)activity.findViewById(R.id.lo_hi);
//		    	TextView lo_hi_text = (TextView)activity.findViewById(R.id.hi_lo_text);
//				hi_lo.setVisibility(View.VISIBLE);
//	    		lo_hi.setVisibility(View.VISIBLE);
//	    		lo_hi_text.setVisibility(View.VISIBLE);
				
				noteOrder.setVisibility(View.VISIBLE);
				noteOrderText.setVisibility(View.VISIBLE);
				interval.setVisibility(View.VISIBLE);
				intervalText.setVisibility(View.VISIBLE);
				leftIndexValue.setVisibility(View.VISIBLE);
				rightIndexValue.setVisibility(View.VISIBLE);
			}
		});
		
		RadioButton challenge = (RadioButton)activity.findViewById(R.id.challenge_option);
		challenge.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
//				RadioButton hi_lo = (RadioButton)activity.findViewById(R.id.hi_lo);
//		    	RadioButton lo_hi = (RadioButton)activity.findViewById(R.id.lo_hi);
//		    	TextView lo_hi_text = (TextView)activity.findViewById(R.id.hi_lo_text);
//				hi_lo.setVisibility(View.INVISIBLE);
//	    		lo_hi.setVisibility(View.INVISIBLE);
//	    		lo_hi_text.setVisibility(View.INVISIBLE);
				
				noteOrder.setVisibility(View.INVISIBLE);
				noteOrderText.setVisibility(View.INVISIBLE);
				interval.setVisibility(View.INVISIBLE);
				intervalText.setVisibility(View.INVISIBLE);
				leftIndexValue.setVisibility(View.INVISIBLE);
				rightIndexValue.setVisibility(View.INVISIBLE);
			}
		});
	}

	public void goToGame() {
		Intent intent = new Intent(activity, GameActivity.class);
		activity.startActivity(intent);
		activity.finish();
	}

	public void storeOptions(){
    	
    	// choosing instrument
		int instrId = instrument.getCheckedRadioButtonId();
		RadioButton instrBtn = (RadioButton) activity.findViewById(instrId);
		int instr = instrument.indexOfChild(instrBtn);
		
		switch(instr){
		case 0:
			game.setInstrumentType(InstrumentTypes.PIANO);
			break;
		case 1:
			game.setInstrumentType(InstrumentTypes.GUITAR);
		}
    	
		// TODO change settings in gameplay instance
    	
    	// choosing difficulty
    	/*if (((String)difficulty.getSelectedItem()).equals("Beginner"))
    	{
    		game.setDifficulty(Difficulties.BEGINNER);
    	}
    	else if (((String)difficulty.getSelectedItem()).equals("Intermediate"))
    	{
    		game.setDifficulty(Difficulties.INTERMEDIATE);
    	}
    	else if (((String)difficulty.getSelectedItem()).equals("Advanced"))
    	{
    		game.setDifficulty(Difficulties.ADVANCED);
    	}*/
    	game.setDifficulty(Difficulties.ADVANCED);
    	
    	// choosing mode

    	int modeId = gameMode.getCheckedRadioButtonId();
    	RadioButton modeBtn = (RadioButton) activity.findViewById(modeId);
    	int mode = gameMode.indexOfChild(modeBtn);
    	
    	switch(mode){
    	case 0:
    		game.setMode(Modes.FREEPLAY);
    		break;
    	case 1:
    		game.setMode(Modes.PRACTICE);
    		break;
    	case 2:
    		game.setMode(Modes.CHALLENGE);
    		break;
    	}
    	
    	
    	
    	// choosing scale
    	/*if(((String)scale.getSelectedItem()).equals("Major"))
    	{
    		game.setScale(ScaleTypes.MAJOR);
    	}
    	if(((String)scale.getSelectedItem()).equals("Pentatonic"))
    	{
    		game.setScale(ScaleTypes.PENTATONIC);
    	}*/
    	game.setScale(ScaleTypes.m2);
    	SoundInfo mi = loadNotes();
    	if (game.getInstrumentType() == InstrumentTypes.PIANO)
    	{
    		IMusicInstrument piano = MusicInstrumentFactory.makeInstrument(mi, InstrumentTypes.PIANO, game.getScale());
    		game.setInstrument(piano);
    	}
    	else if (game.getInstrumentType() == InstrumentTypes.GUITAR)
    	{
    		IMusicInstrument guitar = MusicInstrumentFactory.makeInstrument(mi, InstrumentTypes.GUITAR, game.getScale());
    		game.setInstrument(guitar);
    	}
    	
    	// choosing interval range
        int left = interval.getLeftIndex();
        int right = interval.getRightIndex();
        
        switch(left){
	        case 0:
	        	break;
	        case 1:
	        	break;
	        case 2:
	        	break;
	        case 3:
	        	break;
	        case 4:
	        	break;
	        case 5:
	        	break;
	        case 6:
	        	break;
	        case 7:
	        	break;
	        case 8:
	        	break;
	        case 9:
	        	break;
	        case 10:
	        	break;
	        case 11:
	        	break;
	        default: // set to first interval
        }
    	
        switch(right){
	        case 0:
	        	break;
	        case 1:
	        	break;
	        case 2:
	        	break;
	        case 3:
	        	break;
	        case 4:
	        	break;
	        case 5:
	        	break;
	        case 6:
	        	break;
	        case 7:
	        	break;
	        case 8:
	        	break;
	        case 9:
	        	break;
	        case 10:
	        	break;
	        case 11:
	        	break;
	        default: // set to last interval
        }
        
    	// choosing note order
    	int orderId = noteOrder.getCheckedRadioButtonId();
    	RadioButton orderBtn = (RadioButton) activity.findViewById(orderId);
    	int order = gameMode.indexOfChild(orderBtn);
    	
    	switch(order){
    	case 0: // low-high
    		break;
    	case 1: // high-low
    		break;
    	case 2: // both
    		break;
    	}
    	
    	//saving user preferences
    	LyraProps.getInstance(activity).getUserPreferences().setGameMode(game.getMode());
    	LyraProps.getInstance(activity).getUserPreferences().setInstrumentType(game.getInstrumentType());
    	LyraProps.getInstance(activity).saveProps();
    	
    	// choosing high to low or low to high
    	RadioButton hi_lo = (RadioButton)activity.findViewById(R.id.hi_lo);
    	RadioButton lo_hi = (RadioButton)activity.findViewById(R.id.lo_hi);
    	if (hi_lo.isChecked())
    	{
    		System.out.println("HiLo");
    	}
    	else if (lo_hi.isChecked())
    	{
    		System.out.println("LoHi");
    	}
    }

	private SoundInfo loadNotes() {
		SoundPool sp = new SoundPool(12, AudioManager.STREAM_MUSIC, 0);

		SoundInfo mi = new SoundInfo();

		int[] notes = null;

		// For piano instrument type load the piano sounds
		if (game.getInstrumentType() == InstrumentTypes.PIANO) {
			Class<raw> raw = R.raw.class;
			Field[] fields = raw.getFields();
			ArrayList<Integer> notesList = new ArrayList<Integer>();
			for (int i = 0; i < fields.length; i++) {
				Field f = fields[i];
				if (f.getName().startsWith("p")) {
					try {
						notesList.add(sp.load(activity, f.getInt(null), 1));
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}

			// Convert it to an int array
			notes = new int[notesList.size()];
			for (int i = 0; i < notesList.size(); i++) {
				notes[i] = notesList.get(i).intValue();
			}
		} else if (game.getInstrumentType() == InstrumentTypes.GUITAR) {
			Class<raw> raw = R.raw.class;
			Field[] fields = raw.getFields();
			ArrayList<Integer> notesList = new ArrayList<Integer>();
			for (int i = 0; i < fields.length; i++) {
				Field f = fields[i];
				if (f.getName().startsWith("g")) {
					try {
						notesList.add(sp.load(activity, f.getInt(null), 1));
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}

			// Convert it to an int array
			notes = new int[notesList.size()];
			for (int i = 0; i < notesList.size(); i++) {
				notes[i] = notesList.get(i).intValue();
			}
		}

		mi.setSoundNotes(notes);
		mi.setSoundPool(sp);

		return mi;
	}
}
