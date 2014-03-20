package com.lyra.eartrainer.control;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.media.MediaPlayer;
import android.content.Context;
import android.view.Surface;
import android.widget.VideoView;
import android.widget.MediaController;
import android.net.Uri;

import com.lyra.eartrainer.LeaderBoardActivity;
import com.lyra.eartrainer.MainMenuActivity;
import com.lyra.eartrainer.OptionsActivity;
import com.lyra.eartrainer.R;
import com.lyra.eartrainer.view.MainMenu;

public class MainMenuController extends Controller {
        private MainMenu mmView;
        private MainMenuActivity newMMActivity;
        private VideoView mmVideo;

        public MainMenuController(MainMenuActivity mmActivity) {
                super(mmActivity);
                newMMActivity = mmActivity;
        }

        public void initialize() {
        		activity.setContentView(R.layout.activity_mainmenu);
        		activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                
        		// creating the view
                mmView = new MainMenu(activity);
                      
                // attaching event listeners to view widgets
                attachEvents();
        }

        private void attachEvents() {
                // adding submit button click handler
                Button btnSubmit = (Button) activity.findViewById(R.id.btnToGame);
                btnSubmit.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                // fill in later
                        	goToOptions();
                        }
                });
                Button btnSubmit1 = (Button) activity.findViewById(R.id.btnToSettings);
                btnSubmit1.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // play our video
                        	Button btnSubmit2 = (Button) activity.findViewById(R.id.btnToSettings);
                        	mmVideo = (VideoView) activity.findViewById(R.id.videoView1);
                        	if (mmVideo.isShown())
                        	{
                        		mmVideo.setVisibility(View.INVISIBLE);
                        		btnSubmit2.setText(R.string.tutorial);
                        	}
                        	else
                        	{
                        		mmVideo.setVisibility(View.VISIBLE);
                        		mmVideo.setVideoURI(Uri.parse("android.resource://" + activity.getPackageName() + "/" + R.raw.tutorial));
                            	mmVideo.setMediaController(new MediaController(newMMActivity));
                            	btnSubmit2.setText(R.string.close_tutorial);
                        		mmVideo.requestFocus();
                        		mmVideo.start();
                        	}
                        }
                });
                Button btnSubmit2 = (Button) activity
                                .findViewById(R.id.btnToLeaderboards);
                btnSubmit2.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        	goToActivity(LeaderBoardActivity.class);
                        }
                });
        }
        
        public void goToOptions(){
        	Intent intent = new Intent(activity,OptionsActivity.class);
        	activity.startActivity(intent);
//        	activity.finish();
        }
}