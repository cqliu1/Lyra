package com.lyra.eartrainer.control;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.lang.StringBuilder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.lyra.eartrainer.GameActivity;
import com.lyra.eartrainer.GameOverActivity;
import com.lyra.eartrainer.PauseActivity;
import com.lyra.eartrainer.R;
import com.lyra.eartrainer.dao.DaoErrorInfo;
import com.lyra.eartrainer.dao.DaoParseException;
import com.lyra.eartrainer.dao.LeaderBoardDao;
import com.lyra.eartrainer.dao.LeaderBoardDaoEventListener;
import com.lyra.eartrainer.dao.LeaderBoardDaoImpl;
import com.lyra.eartrainer.model.GamePlay;
import com.lyra.eartrainer.model.LeaderBoardEntry;
import com.lyra.eartrainer.model.globals.Difficulties;
import com.lyra.eartrainer.model.globals.InstrumentTypes;
import com.lyra.eartrainer.model.globals.Modes;
import com.lyra.eartrainer.model.instrument.Piano;
import com.lyra.eartrainer.properties.LyraProps;
import com.lyra.eartrainer.view.GameInterface;

public class GameController extends Controller {
	private GameInterface gameView;
	private Context con;
	private String note;
	private GamePlay game;
	private int MAX_STRIKES;	// varies depending on difficulty
	private ImageButton[] keys;
	private ImageButton replay;
	private ImageButton pause;
	private ToggleButton showHide;

	public GameController(GameActivity gameActivity) {
		super(gameActivity);
	}

	public void initialize() {
		game = GamePlay.instance();
		
		// set layout by instrument
		if(game.getInstrumentType() == InstrumentTypes.PIANO)
			activity.setContentView(R.layout.piano);
		else if(game.getInstrumentType() == InstrumentTypes.GUITAR)
			activity.setContentView(R.layout.guitar);
		activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
		
		gameView = new GameInterface(activity,game);
		
		// grab difficulty of game
		if (game.getDifficulty() == Difficulties.BEGINNER) MAX_STRIKES = 10; 
		else if (game.getDifficulty() == Difficulties.INTERMEDIATE) MAX_STRIKES = 5;
		else if (game.getDifficulty() == Difficulties.ADVANCED) MAX_STRIKES = 3; 
		
		initializeLeaderBoardDao(gameView);
		attachEvents();
		
        //if the eyeball is closed then show the keys that don't have the notes on them
		if(!LyraProps.getInstance(activity).getUserPreferences().isShownKeyNotes()){
			showHide.setChecked(false);
			gameView.swapKeys();
		}
		
		if(game.getMode() == Modes.FREEPLAY){
			replay.setVisibility(View.INVISIBLE);
			gameView.hideNoteDisplay();
			gameView.hideScores();
		} else{
			replay.setVisibility(View.VISIBLE);
			gameView.showNoteDisplay();
			gameView.showScores();
		}
	}
	
	private void attachEvents(){
		//adding submit button click handler
		replay = (ImageButton) activity.findViewById(R.id.replay_button);
        pause = (ImageButton) activity.findViewById(R.id.pause_button);
        showHide = (ToggleButton) activity.findViewById(R.id.show_hide);
        
        if (game.getInstrumentType() == InstrumentTypes.PIANO)
        {
	        keys = new ImageButton[25];
	        
	       	keys[0] = (ImageButton) activity.findViewById(R.id.key1);
	        keys[1] = (ImageButton) activity.findViewById(R.id.key2);
	        keys[2] = (ImageButton) activity.findViewById(R.id.key3);
	        keys[3] = (ImageButton) activity.findViewById(R.id.key4);
	        keys[4] = (ImageButton) activity.findViewById(R.id.key5);
	        keys[5] = (ImageButton) activity.findViewById(R.id.key6);
	        keys[6] = (ImageButton) activity.findViewById(R.id.key7);
	        keys[7] = (ImageButton) activity.findViewById(R.id.key8);
	        keys[8] = (ImageButton) activity.findViewById(R.id.key9);
	        keys[9] = (ImageButton) activity.findViewById(R.id.key10);
	        keys[10] = (ImageButton) activity.findViewById(R.id.key11);
	        keys[11] = (ImageButton) activity.findViewById(R.id.key12);
	        keys[12] = (ImageButton) activity.findViewById(R.id.key13);
	        keys[13] = (ImageButton) activity.findViewById(R.id.key14);
	        keys[14] = (ImageButton) activity.findViewById(R.id.key15);
	        keys[15] = (ImageButton) activity.findViewById(R.id.key16);
	        keys[16] = (ImageButton) activity.findViewById(R.id.key17);
	        keys[17] = (ImageButton) activity.findViewById(R.id.key18);
	        keys[18] = (ImageButton) activity.findViewById(R.id.key19);
	        keys[19] = (ImageButton) activity.findViewById(R.id.key20);
	        keys[20] = (ImageButton) activity.findViewById(R.id.key21);
	        keys[21] = (ImageButton) activity.findViewById(R.id.key22);
	        keys[22] = (ImageButton) activity.findViewById(R.id.key23);
	        keys[23] = (ImageButton) activity.findViewById(R.id.key24);
	        keys[24] = (ImageButton) activity.findViewById(R.id.key25);
	        
        }
        else if (game.getInstrumentType() == InstrumentTypes.GUITAR){
        	keys = new ImageButton[36];
        	
        	keys[0] = (ImageButton) activity.findViewById(R.id.tab1);
        	keys[1] = (ImageButton) activity.findViewById(R.id.tab2);
        	keys[2] = (ImageButton) activity.findViewById(R.id.tab3);
        	keys[3] = (ImageButton) activity.findViewById(R.id.tab4);
        	keys[4] = (ImageButton) activity.findViewById(R.id.tab5);
        	keys[5] = (ImageButton) activity.findViewById(R.id.tab6);
        	keys[6] = (ImageButton) activity.findViewById(R.id.tab7);
        	keys[7] = (ImageButton) activity.findViewById(R.id.tab8);
        	keys[8] = (ImageButton) activity.findViewById(R.id.tab9);
        	keys[9] = (ImageButton) activity.findViewById(R.id.tab10);
        	keys[10] = (ImageButton) activity.findViewById(R.id.tab11);
        	keys[11] = (ImageButton) activity.findViewById(R.id.tab12);
        	keys[12] = (ImageButton) activity.findViewById(R.id.tab13);
        	keys[13] = (ImageButton) activity.findViewById(R.id.tab14);
        	keys[14] = (ImageButton) activity.findViewById(R.id.tab15);
        	keys[15] = (ImageButton) activity.findViewById(R.id.tab16);
        	keys[16] = (ImageButton) activity.findViewById(R.id.tab17);
        	keys[17] = (ImageButton) activity.findViewById(R.id.tab18);
        	keys[18] = (ImageButton) activity.findViewById(R.id.tab19);
        	keys[19] = (ImageButton) activity.findViewById(R.id.tab20);
        	keys[20] = (ImageButton) activity.findViewById(R.id.tab21);
        	keys[21] = (ImageButton) activity.findViewById(R.id.tab22);
        	keys[22] = (ImageButton) activity.findViewById(R.id.tab23);
        	keys[23] = (ImageButton) activity.findViewById(R.id.tab24);
        	keys[24] = (ImageButton) activity.findViewById(R.id.tab25);
        	keys[25] = (ImageButton) activity.findViewById(R.id.tab26);
        	keys[26] = (ImageButton) activity.findViewById(R.id.tab27);
        	keys[27] = (ImageButton) activity.findViewById(R.id.tab28);
        	keys[28] = (ImageButton) activity.findViewById(R.id.tab29);
        	keys[29] = (ImageButton) activity.findViewById(R.id.tab30);
        	keys[30] = (ImageButton) activity.findViewById(R.id.tab31);
        	keys[31] = (ImageButton) activity.findViewById(R.id.tab32);
        	keys[32] = (ImageButton) activity.findViewById(R.id.tab33);
        	keys[33] = (ImageButton) activity.findViewById(R.id.tab34);
        	keys[34] = (ImageButton) activity.findViewById(R.id.tab35);
        	keys[35] = (ImageButton) activity.findViewById(R.id.tab36);
        }
        replay.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(game.getCurrentRound() == null) {
					game.startNewRound();
					gameView.updateStrikes();
				}
				replayNotes(con,note);
			}
		});
        
        pause.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
				goToPause();
			}
		});
        
        showHide.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				LyraProps.getInstance(activity).getUserPreferences().setShownKeyNotes(isChecked);
				LyraProps.getInstance(activity).saveProps();
				gameView.swapKeys();
			}
		});
        
        // for challenge mode/practice mode
        if (GamePlay.instance().getMode() != Modes.FREEPLAY)
        {
	        // Set the touch listener for each key
	        for(int i = 0; i < keys.length; i++ ) {       	
	        	final int note = i;
	        	final Activity act = this.activity;
	        	
	        	keys[i].setOnTouchListener(new View.OnTouchListener() {
					
	        		int lastPlayedKey = -1;
	        		
					@Override
					public boolean onTouch(View v, MotionEvent event) {	
						// If it is the initial touch, play the note and update the score
						if(event.getActionMasked() == MotionEvent.ACTION_DOWN){ 
							// If the game isn't started or has ended, just display a toast to restart
							if(game.getCurrentRound() == null || game.getCurrentRound().getFinished()) {
								Toast.makeText(act, "Press play to start!", Toast.LENGTH_SHORT).show();
								return true;
							}
							
							Timer timer = new Timer();
							timer.schedule(new PlayNoteTask(note, act), 0L);
							
							if(game.getCurrentRound().isCorrect(note)) {
								//This works fine for piano but not for guitar, because guitar has repeated notes.
								updateGameScore();
								gameView.selectCorrectNote(note);					
								game.getCurrentRound().setFinished(true);
								
								if(game.isGameOver()) {
									
								} else {
									StringBuilder interval = new StringBuilder();
									int theInterval = game.getCurrentRound().getInterval();
									if (theInterval > 13) {
										//The interval spans an octave, present it differently
										interval.append("Octave ");
										theInterval = theInterval%12;
									}
									switch(theInterval) {
									case 1: interval.append("minor 2nd");
										break;
									case 2: interval.append("Major 2nd");
									break;
									case 3: interval.append("minor 3rd");
									break;
									case 4: interval.append("Major 3rd");
									break;
									case 5: interval.append("Perfect 4th");
									break;
									case 6: interval.append("diminished 5th");
									break;
									case 7: interval.append("Perfect 5th");
									break;
									case 8: interval.append("minor 6th");
									break;
									case 9: interval.append("Major 6th");
									break;
									case 10: interval.append("minor 7th");
									break;
									case 11: interval.append("Major 7th");
									break;
									case 12: interval.append("Octave");
									break;
									default: break;
									}
									Toast.makeText(act, interval.toString(), Toast.LENGTH_SHORT).show();
									timer.schedule(new EndRoundTask(act), 1000L);
								}
								
							} else {
								gameView.selectIncorrectNote(note);
								checkStrikes(timer, act);
							}
							if(game.isGameOver()) {
								addScore(gameView);
								Toast.makeText(act, "Game Over!", Toast.LENGTH_SHORT).show();
								timer.schedule(new EndGameTask(act), 2000L);
							}
							timer.schedule(new ResetNoteTask(note, act), 1000L);
								
							lastPlayedKey = note;
							return true;
						}
						// If it is a hover, figure out which key is hovered and play it
						if(event.getActionMasked() == MotionEvent.ACTION_MOVE) {
							// Do nothing if the game hasn't started or is finished
							if(game.getCurrentRound() == null || game.getCurrentRound().getFinished()) {
								return true;
							}
							
	        				for(Integer hoverKey : getKeysHovered(v, event)) {
								//Log.d("Piano", "hoverKey = " + hoverKey);
								if(hoverKey != -1 && hoverKey != lastPlayedKey) {
									Timer timer = new Timer();
									timer.schedule(new PlayNoteTask(hoverKey, act), 0L);
									
									if(game.getCurrentRound().isCorrect(hoverKey)) {
										updateGameScore();
										gameView.selectCorrectNote(hoverKey);
										game.getCurrentRound().setFinished(true);
										
										if(game.isGameOver()) {
											
										} else {
											Toast.makeText(act, "Round completed!", Toast.LENGTH_SHORT).show();
											timer.schedule(new EndRoundTask(act), 1000L);
										}
									} else {
										gameView.selectIncorrectNote(hoverKey);
										checkStrikes(timer, act);
									}
									if(game.isGameOver()) {
										Toast.makeText(act, "Game Over!", Toast.LENGTH_SHORT).show();
										timer.schedule(new EndGameTask(act), 2000L);
										addScore(gameView);
									}
									timer.schedule(new ResetNoteTask(hoverKey, act), 1000L);
										
									lastPlayedKey = hoverKey;
									return true;
								}	
	        				} // for hoverKey in getKeysHovered
						}
						return false;
					}
				});
	        }
        }
        // its free play, just listen for notes and play when clicked
        else
        {
        	for (int i = 0; i < keys.length; i++)
        	{
        		final int note = i;
        		
	        	final Activity act = this.activity;
	        	
	        	keys[i].setOnTouchListener(new View.OnTouchListener() 
	        	{
	        		int lastPlayedKey = -1;
	        		
	        		public boolean onTouch(View v, MotionEvent event) 
	        		{			
	        			if (event.getActionMasked() == MotionEvent.ACTION_DOWN)
	        			{
							Timer timer = new Timer();
							timer.schedule(new PlayNoteTask(note, act), 0L);
							timer.schedule(new ResetNoteTask(note, act), 1000L);
							lastPlayedKey = note;
							return true;
	        			}
	        			if (event.getActionMasked() == MotionEvent.ACTION_MOVE)
	        			{
	        				for(Integer hoverKey : getKeysHovered(v, event)){
								if(hoverKey != -1 && hoverKey != lastPlayedKey) {
									Timer timer = new Timer();
									timer.schedule(new PlayNoteTask(hoverKey, act), 0L);
									timer.schedule(new ResetNoteTask(hoverKey, act), 1000L);
										
									lastPlayedKey = hoverKey;
									return true;
								}
	        				}
	        			}
	        			return false;
					}
	        		
	        		
	        	});
        	}
        }
	}
	
	public void replayNotes(Context con, String note) {
		int firstNote = game.getCurrentRound().getFirstNote();
		int secondNote = game.getCurrentRound().getSecondNote();
		
		Timer timer = new Timer();
		timer.schedule(new ResetNoteTask(firstNote, this.activity), 0L);
		timer.schedule(new SelectNoteTask(firstNote, this.activity, true), 100L);
		timer.schedule(new PlayNoteTask(firstNote, this.activity), 100L);
		timer.schedule(new PlayNoteTask(secondNote, this.activity), 1000L);
	}

	public void goToPause(){
		Intent intent = new Intent(activity,PauseActivity.class);
		activity.startActivityForResult(intent,GameActivity.GAME_CANCELLED);
//		activity.finish();
	}
	
	public void checkStrikes(Timer timer, Activity act)
	{
		if (game.getMode() == Modes.CHALLENGE)
		{
			game.oneStrike();
			gameView.updateStrikes();
			if (game.getStrikes() >= MAX_STRIKES) 
			{
				game.getCurrentRound().setFinished(true);
				Toast.makeText(act, "Round failed!", Toast.LENGTH_SHORT).show();
				int correctNote = -1;
				for(int i = 0; i < keys.length; i++ ) 
				{       	
			        if(game.getCurrentRound().isCorrect(i)) 
			        {
			        	correctNote = i;
			        	break;
			        }
				}
				gameView.selectCorrectNote(correctNote);
				game.getCurrentRound().setFinished(true);
				timer.schedule(new EndRoundTask(act), 1000L);
			}
			
		}
	}
	
	private void updateGameScore() {
		if(game.getCurrentRound() != null && !game.getCurrentRound().getFinished()) {
			int oldScore = game.getScore();
			game.setScore(oldScore+10);
			gameView.updateScore();
		}
	}
	
	// Loops through the keys to see which one contains the event using x/y coords
	private ArrayList<Integer> getKeysHovered(View v, MotionEvent event) {
		System.out.println("MotionEvent group");
		int[] location = new int[2];
		v.getLocationOnScreen(location);
		
		ArrayList<Integer> keysHovered = new ArrayList<Integer>();
		
		// For each historical event get the key hovered
		for(int i=0; i<event.getHistorySize(); i++) {
			v.getLocationOnScreen(location);
			float eventX = event.getHistoricalX(i) + location[0];
			float eventY = event.getHistoricalY(i) + location[1];
			
			keysHovered.add(getKeyHovered(location, eventX, eventY));
		}
		
		// Get the key hovered for the current coordinates
		v.getLocationOnScreen(location);
		float eventX = event.getX() + location[0];
		float eventY = event.getY() + location[1];
		
		keysHovered.add(getKeyHovered(location, eventX, eventY));	
		
		return keysHovered;
	}
	
	private int getKeyHovered(int[] location, float eventRealX, float eventRealY) {
		// Iterate through the keys forwards
		for(int i=0; i<keys.length; i++) {
			ImageButton thisKey = keys[i], nextKey = null;		
			if(i < keys.length-1) {
				nextKey = keys[i+1];
			}
			
			thisKey.getLocationOnScreen(location);
			int thisLeft = location[0];
			int thisRight = location[0] + thisKey.getWidth();
			int thisTop = location[1];
			int thisBottom = location[1] + thisKey.getHeight();			
			
			// Check if we are in the bounds of this key
			if(eventRealX > thisLeft && eventRealX < thisRight
					&& eventRealY < thisBottom && eventRealY > thisTop) {
				
				// For pianos, check if we are on a black key and give them precedence over white keys
				// when overlapping
				
				if(game.getInstrumentType() == InstrumentTypes.PIANO){
					
					Piano piano = (Piano) game.getInstrument();
					
					// If this is a black key, return it as the hovered
					if(piano.isBlackKey(i)) {
						return i;
					}
					
					// Check if the nextKey is black and overlapping
					if(nextKey != null && piano.isBlackKey(i+1)) {
						nextKey.getLocationOnScreen(location);
						int nextLeft = location[0];
						int nextRight = location[0] + nextKey.getWidth();
						int nextTop = location[1];
						int nextBottom = location[1] + nextKey.getHeight();					
						
						// Check if in the bounds of the next key
						if(eventRealX > nextLeft && eventRealX < nextRight
								&& eventRealY < nextBottom && eventRealY > nextTop) {
							return i+1;
						}
						
					}
					
				}
				
				// guitar does not have any overlapping keys, not a special case
				
				// No special cases, Return the key we are in the bounds of
				return i;
			}		
		}
		return -1;
	}
	
   //tells handler to send a message
   class ResetNoteTask extends TimerTask {

	   int note;
	   Activity act;
	   
	   public ResetNoteTask(int note, Activity act) {
		   this.note = note;
		   this.act = act;
	   }
	   
	   @Override
       public void run() {
           act.runOnUiThread(new Runnable() {

               @Override
               public void run() {
            	   gameView.resetNote(note);
               }
           });
       }
   };
   
   //tells handler to send a message
   class PlayNoteTask extends TimerTask {

	   int note;
	   Activity act;
	   
	   public PlayNoteTask(int note, Activity act) {
		   this.note = note;
		   this.act = act;
		   //System.out.println("Playing note " + note);
	   }
	   
	   @Override
       public void run() {
           act.runOnUiThread(new Runnable() {

               @Override
               public void run() {
            	   GamePlay.instance().getInstrument().playNote(note);
               }
           });
       }
   };
   
   //tells handler to send a message
   class SelectNoteTask extends TimerTask {

	   int note;
	   Activity act;
	   boolean correct;
	   
	   public SelectNoteTask(int note, Activity act, boolean correct) {
		   this.note = note;
		   this.act = act;
		   this.correct = correct;
	   }
	   
	   @Override
       public void run() {
           act.runOnUiThread(new Runnable() {

               @Override
               public void run() {
            	   if(correct)
            		   gameView.selectCorrectNote(note);
            	   else
            		   gameView.selectIncorrectNote(note);
               }
           });
       }
   };
   
   //tells handler to send a message
   class EndRoundTask extends TimerTask {

	   Activity act;
	   
	   public EndRoundTask(Activity act) {
		   this.act = act;
	   }
	   
	   @Override
       public void run() {
           act.runOnUiThread(new Runnable() {

               @Override
               public void run() {
            	   gameView.resetNote(game.getCurrentRound().getFirstNote());
            	   gameView.resetNote(game.getCurrentRound().getSecondNote());
            	   game.endCurrentRound();
               }
           });
       }
   };
   
   class EndGameTask extends TimerTask {

	   Activity act;
	   
	   public EndGameTask(Activity act) {
		   this.act = act;
	   }
	   
	   @Override
       public void run() {
           act.runOnUiThread(new Runnable() {

               @Override
               public void run() {
	           		Intent intent = new Intent(activity,GameOverActivity.class);
	        		activity.startActivity(intent);
	        		//this.getParent().finish();
	        		activity.finish();
               }
           });
       }
   };
   
	
}
