package com.lyra.eartrainer.control;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.lyra.eartrainer.GameActivity;
import com.lyra.eartrainer.PauseActivity;
import com.lyra.eartrainer.R;
import com.lyra.eartrainer.model.GamePlay;
import com.lyra.eartrainer.view.GameInterface;

public class GameController extends Controller {
	private GameInterface gameView;
	private Context con;
	private String note;
	private GamePlay game;
	private SoundPool sp;
	private int[] notes;
	private int currentNote;

	public GameController(GameActivity gameActivity) {
		super(gameActivity);
	}

	public void initialize() {
		
		activity.setContentView(R.layout.piano);
		activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		
		game = GamePlay.instance();
		gameView = new GameInterface(activity,game);
		
		sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);

        notes = new int[13];
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
        
		attachEvents();
	}
	
	private void attachEvents(){
		//adding submit button click handler
		ImageButton replay = (ImageButton) activity.findViewById(R.id.replay_button);
        ImageButton pause = (ImageButton) activity.findViewById(R.id.pause_button);
        TextView score = (TextView) activity.findViewById(R.id.score);
        score.setText(""+game.getScore());

        ImageButton[] keys = new ImageButton[13];
        
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
        	
        	keys[i].setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					playNote(note);
					int oldScore = game.getScore();
					game.setScore(++oldScore);
					gameView.updateScore();
				}
			});
        }
	}
	
    public void playNote(int note) {
		// TODO Auto-generated method stub
//		game.setScore(0);
		//Toast.makeText(game, note, Toast.LENGTH_SHORT).show();
		
		// this is where we set the volume
		//float vol = (float)newVolume/100;
		sp.play(notes[note], 1.0f, 1.0f, 0, 0, 1.0f);
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
}
