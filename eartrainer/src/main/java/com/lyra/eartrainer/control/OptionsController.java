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

		leftIndexValue.setText("m2");
		rightIndexValue.setText("O");
        
		// Practice mode options are invisible by default
		hideHighLow();
		
		// Make visible if mode is practice
		if(LyraProps.getInstance(activity).getUserPreferences().getGameMode() == Modes.PRACTICE){
			showHighLow();
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
            	switch(leftThumbIndex) {
				case 0: leftIndexValue.setText("m2");
					break;
				case 1: leftIndexValue.setText("M2");
				break;
				case 2: leftIndexValue.setText("m3");
				break;
				case 3: leftIndexValue.setText("M3");
				break;
				case 4: leftIndexValue.setText("P4");
				break;
				case 5: leftIndexValue.setText("d5");
				break;
				case 6: leftIndexValue.setText("P5");
				break;
				case 7: leftIndexValue.setText("m6");
				break;
				case 8: leftIndexValue.setText("M6");
				break;
				case 9: leftIndexValue.setText("m7");
				break;
				case 10: leftIndexValue.setText("M7");
				break;
				case 11: leftIndexValue.setText("O");
				break;
				default: break;
            	}
            	switch(rightThumbIndex) {
				case 0: rightIndexValue.setText("m2");
					break;
				case 1: rightIndexValue.setText("M2");
				break;
				case 2: rightIndexValue.setText("m3");
				break;
				case 3: rightIndexValue.setText("M3");
				break;
				case 4: rightIndexValue.setText("P4");
				break;
				case 5: rightIndexValue.setText("d5");
				break;
				case 6: rightIndexValue.setText("P5");
				break;
				case 7: rightIndexValue.setText("m6");
				break;
				case 8: rightIndexValue.setText("M6");
				break;
				case 9: rightIndexValue.setText("m7");
				break;
				case 10: rightIndexValue.setText("M7");
				break;
				case 11: rightIndexValue.setText("O");
				break;
				default: break;
            	}
            }
        });
        
		RadioButton freeplay = (RadioButton)activity.findViewById(R.id.freeplay_option);
		freeplay.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				hideHighLow();
				storeOptions();
			}
		});
		
		RadioButton practice = (RadioButton)activity.findViewById(R.id.practice_option);
		practice.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				showHighLow();
				storeOptions();
			}
		});
		
		RadioButton challenge = (RadioButton)activity.findViewById(R.id.challenge_option);
		challenge.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				hideHighLow();
				storeOptions();
			}
		});
	}
	
	private void hideHighLow() {
		noteOrder.setVisibility(View.INVISIBLE);
		noteOrderText.setVisibility(View.INVISIBLE);
		interval.setVisibility(View.INVISIBLE);
		intervalText.setVisibility(View.INVISIBLE);
		leftIndexValue.setVisibility(View.INVISIBLE);
		rightIndexValue.setVisibility(View.INVISIBLE);
	}
	
	private void showHighLow() {
		noteOrder.setVisibility(View.VISIBLE);
		noteOrderText.setVisibility(View.VISIBLE);
		interval.setVisibility(View.VISIBLE);
		intervalText.setVisibility(View.VISIBLE);
		leftIndexValue.setVisibility(View.VISIBLE);
		rightIndexValue.setVisibility(View.VISIBLE);
	}

	public void goToGame() {
		// Store the options
		storeOptions();
		
		// Load the sound files
		SoundInfo sound = loadNotes();
		
		// Create the instrument
		IMusicInstrument mi = null;
    	if (game.getInstrumentType() == InstrumentTypes.PIANO)
    	{
    		mi = MusicInstrumentFactory.makeInstrument(sound, InstrumentTypes.PIANO, game.getScale());
    	}
    	else if (game.getInstrumentType() == InstrumentTypes.GUITAR)
    	{
    		mi = MusicInstrumentFactory.makeInstrument(sound, InstrumentTypes.GUITAR, game.getScale());
    	}
    	game.setInstrument(mi);
    	
    	// Start the activity
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
			break;
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
    	
    	// choosing interval range
        int left = interval.getLeftIndex();
        int right = interval.getRightIndex();
        
        switch(left){
	        case 0:
	        	game.setLeftInterval(ScaleTypes.m2);
	        	break;
	        case 1:
	        	game.setLeftInterval(ScaleTypes.M2);
	        	break;
	        case 2:
	        	game.setLeftInterval(ScaleTypes.m3);
	        	break;
	        case 3:
	        	game.setLeftInterval(ScaleTypes.M3);
	        	break;
	        case 4:
	        	game.setLeftInterval(ScaleTypes.P4);
	        	break;
	        case 5:
	        	game.setLeftInterval(ScaleTypes.d5);
	        	break;
	        case 6:
	        	game.setLeftInterval(ScaleTypes.P5);
	        	break;
	        case 7:
	        	game.setLeftInterval(ScaleTypes.m6);
	        	break;
	        case 8:
	        	game.setLeftInterval(ScaleTypes.M6);
	        	break;
	        case 9:
	        	game.setLeftInterval(ScaleTypes.m7);
	        	break;
	        case 10:
	        	game.setLeftInterval(ScaleTypes.M7);
	        	break;
	        case 11:
	        	game.setLeftInterval(ScaleTypes.O);
	        	break;
	        default: // set to first interval
	        	game.setLeftInterval(ScaleTypes.m2);
	        	break;
        }
    	
        switch(right){
        case 0:
        	game.setRightInterval(ScaleTypes.m2);
        	break;
        case 1:
        	game.setRightInterval(ScaleTypes.M2);
        	break;
        case 2:
        	game.setRightInterval(ScaleTypes.m3);
        	break;
        case 3:
        	game.setRightInterval(ScaleTypes.M3);
        	break;
        case 4:
        	game.setRightInterval(ScaleTypes.P4);
        	break;
        case 5:
        	game.setRightInterval(ScaleTypes.d5);
        	break;
        case 6:
        	game.setRightInterval(ScaleTypes.P5);
        	break;
        case 7:
        	game.setRightInterval(ScaleTypes.m6);
        	break;
        case 8:
        	game.setRightInterval(ScaleTypes.M6);
        	break;
        case 9:
        	game.setRightInterval(ScaleTypes.m7);
        	break;
        case 10:
        	game.setRightInterval(ScaleTypes.M7);
        	break;
        case 11:
        	game.setRightInterval(ScaleTypes.O);
        	break;
        default: // set to first interval
        	game.setRightInterval(ScaleTypes.m2);
        	break;
        }
        
    	// choosing note order
    	int orderId = noteOrder.getCheckedRadioButtonId();
    	RadioButton orderBtn = (RadioButton) activity.findViewById(orderId);
    	int order = gameMode.indexOfChild(orderBtn);
    	
    	switch(order){
    	case 0: // low-high
    		game.setHiLo(HiLo.LoHi);
    		break;
    	case 1: // high-low
    		game.setHiLo(HiLo.HiLo);
    		break;
    	case 2: // both
    		game.setHiLo(HiLo.Both);
    		break;
    	}
    	
    	//saving user preferences
    	LyraProps.getInstance(activity).getUserPreferences().setGameMode(game.getMode());
    	LyraProps.getInstance(activity).getUserPreferences().setInstrumentType(game.getInstrumentType());
    	LyraProps.getInstance(activity).saveProps();
    	
    	// choosing high to low or low to high
    	RadioButton hi_lo = (RadioButton)activity.findViewById(R.id.hi_lo_option);
    	RadioButton lo_hi = (RadioButton)activity.findViewById(R.id.lo_hi_option);
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
