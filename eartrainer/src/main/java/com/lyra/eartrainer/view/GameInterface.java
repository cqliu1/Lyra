package com.lyra.eartrainer.view;

import com.lyra.eartrainer.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class GameInterface extends Activity {
	
	private ImageButton replay;
	private ImageButton pause;
	private TextView score;
	private int scoreNum;
	private ImageButton[] keys;
	 /**
     * Called when the activity is first created.
     * @param savedInstanceState If the activity is being re-initialized after 
     * previously being shut down then this Bundle contains the data it most 
     * recently supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.piano);
        this.replay = (ImageButton) this.findViewById(R.id.replay_button);
        this.pause = (ImageButton) this.findViewById(R.id.pause_button);
        this.score = (TextView) this.findViewById(R.id.score);
        this.scoreNum = 0;
        this.score.setText(""+this.scoreNum);
        
        this.keys = new ImageButton[13];
        this.keys[0] = (ImageButton) this.findViewById(R.id.key1);
        this.keys[1] = (ImageButton) this.findViewById(R.id.key2);
        this.keys[2] = (ImageButton) this.findViewById(R.id.key3);
        this.keys[3] = (ImageButton) this.findViewById(R.id.key4);
        this.keys[4] = (ImageButton) this.findViewById(R.id.key5);
        this.keys[5] = (ImageButton) this.findViewById(R.id.key6);
        this.keys[6] = (ImageButton) this.findViewById(R.id.key7);
        this.keys[7] = (ImageButton) this.findViewById(R.id.key8);
        this.keys[8] = (ImageButton) this.findViewById(R.id.key9);
        this.keys[9] = (ImageButton) this.findViewById(R.id.key10);
        this.keys[10] = (ImageButton) this.findViewById(R.id.key11);
        this.keys[11] = (ImageButton) this.findViewById(R.id.key12);
        this.keys[12] = (ImageButton) this.findViewById(R.id.key13);
        
        this.replay.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(v==replay){
					Toast.makeText(v.getContext(), "Replay clicked!", Toast.LENGTH_SHORT).show();
					scoreNum++;
					score.setText(""+scoreNum);
				}
			}
		});
        
        this.pause.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		ImageButton button = (ImageButton) v;
				if(button==pause){
					Toast.makeText(v.getContext(), "Pause clicked!", Toast.LENGTH_SHORT).show();
					scoreNum++;
					score.setText(""+scoreNum);
				}
			}
		});
        
        for(int i = 0; i < keys.length; i++ )
        {
        	this.keys[i].setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					Toast.makeText(v.getContext(), v.getContentDescription(), Toast.LENGTH_SHORT).show();
					scoreNum++;
					score.setText(""+scoreNum);
				}
			});
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(com.lyra.eartrainer.R.menu.main, menu);
	return true;
    }

}
