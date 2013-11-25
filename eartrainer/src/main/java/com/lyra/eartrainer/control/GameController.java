package com.lyra.eartrainer.control;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.lyra.eartrainer.GameActivity;
import com.lyra.eartrainer.PauseActivity;
import com.lyra.eartrainer.R;
import com.lyra.eartrainer.model.GamePlay;
import com.lyra.eartrainer.model.instrument.MusicInstrument;
import com.lyra.eartrainer.view.GameInterface;

public class GameController extends Controller {
	private GameInterface gameView;
	private Context con;
	private String note;
	private GamePlay game;
	private int currentNote;
	ImageButton[] keys;

	public GameController(GameActivity gameActivity) {
		super(gameActivity);
	}

	public void initialize() {
		
		activity.setContentView(R.layout.piano);
		activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		
		game = GamePlay.instance();
		gameView = new GameInterface(activity,game);
        
		attachEvents();
	}
	
	private void attachEvents(){
		//adding submit button click handler
		ImageButton replay = (ImageButton) activity.findViewById(R.id.replay_button);
        ImageButton pause = (ImageButton) activity.findViewById(R.id.pause_button);

        keys = new ImageButton[13];
        
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
        
        replay.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				replayNotes(con,note);
				int oldScore = game.getScore();
				game.setScore(++oldScore);
				gameView.updateScore();
			}
		});
        
        pause.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
				goToPause();
			}
		});
        
        for(int i = 0; i < keys.length; i++ )
        {
        	
        	final int note = i;
        	
        	/*keys[i].setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					game.getInstrument().playNote(note);
					int oldScore = game.getScore();
					game.setScore(++oldScore);
					gameView.updateScore();
				}
			});*/
        	
        	
        	
        	keys[i].setOnTouchListener(new View.OnTouchListener() {
				
        		int lastPlayedKey = -1;
        		
				@Override
				public boolean onTouch(View v, MotionEvent event) {	
					//Log.d("Piano", "EventX: " + event.getX() + " EventY: " + event.getY());
					if(event.getActionMasked() == MotionEvent.ACTION_DOWN){ 			
						game.getInstrument().playNote(note);
						int oldScore = game.getScore();
						game.setScore(++oldScore);
						gameView.updateScore();
						lastPlayedKey = note;
						return true;
					}
					if(event.getActionMasked() == MotionEvent.ACTION_MOVE) {
						int hoverKey = getKeyHovered(v, event);
						Log.d("Piano", "hoverKey = " + hoverKey);
						if(hoverKey != -1 && hoverKey != lastPlayedKey) {
							game.getInstrument().playNote(hoverKey);
							int oldScore = game.getScore();
							game.setScore(++oldScore);
							gameView.updateScore();
							lastPlayedKey = hoverKey;
							return true;
						}						
					}
					return false;
				}
			});
        	
        }
	}
	
	public void replayNotes(Context con, String note) {
		// TODO Auto-generated method stub
		game.setScore(0);
		Toast.makeText(activity, note, Toast.LENGTH_SHORT).show();
	}

	public void goToPause(){
		Intent intent = new Intent(activity,PauseActivity.class);
		activity.startActivity(intent);
	}
	
	// Loops through the keys to see which one contains the event using x/y coords
	public int getKeyHovered(View v, MotionEvent event) {
		int[] location = new int[2];
		v.getLocationOnScreen(location);
		
		// Get the event coordinates offset by the location of the view
		float eventRealX = event.getX() + location[0];
		float eventRealY = event.getY() + location[1];
		
		// variables to hold next/current/previous key values
		
		
		for(int i=0; i<keys.length; i++) {
			ImageButton thisKey = keys[i], prevKey = null, nextKey = null;		
			if(i> 0) {
				prevKey = keys[i-1];
			}
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
				
				// Check if we are on a black key by seeing if we overlap the prev and next keys
				if(prevKey != null && nextKey != null) {
					prevKey.getLocationOnScreen(location);
					int prevLeft = location[0];
					int prevRight = location[0] + prevKey.getWidth();
					int prevTop = location[1];
					int prevBottom = location[1] + prevKey.getHeight();
					
					nextKey.getLocationOnScreen(location);
					int nextLeft = location[0];
					int nextRight = location[0] + nextKey.getWidth();
					int nextTop = location[1];
					int nextBottom = location[1] + nextKey.getHeight();					
					
					// Check if in the bounds of the next key
					if(eventRealX > nextLeft && eventRealX < nextRight
							&& eventRealY < nextBottom && eventRealY > nextTop) {
						
						// If this key or the next key is black
						if(nextLeft < thisRight) {
							// If this key is black
							if(prevRight > thisLeft) {
								return i;
							} else {
							// The next key is black
								return i+1;
							}
						// The next key is white
						} else {
							return i+1;
						}
						
					}
					
				}
				
				return i;
			}
			
		}
		return -1;
	}
	
}
