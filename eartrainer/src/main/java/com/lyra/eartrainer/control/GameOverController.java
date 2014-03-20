package com.lyra.eartrainer.control;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.lyra.eartrainer.GameOverActivity;
import com.lyra.eartrainer.LeaderBoardActivity;
import com.lyra.eartrainer.MainMenuActivity;
import com.lyra.eartrainer.OptionsActivity;
import com.lyra.eartrainer.R;
import com.lyra.eartrainer.view.GameOver;

public class GameOverController extends Controller {
        private GameOver cgoView;
         
        public GameOverController(GameOverActivity gameOverActivity){
                super(gameOverActivity);
        } 
        
        public void initialize(){
                //creating the view
        		activity.setContentView(R.layout.activity_gameover);
        		activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                cgoView = new GameOver(activity);
                //attaching event listeners to view widgets
                attachEvents();
        }

        private void attachEvents(){
                //adding submit button click handler
                Button btnToReplay = (Button)activity.findViewById(R.id.btnToReplay);
                btnToReplay.setOnClickListener(new OnClickListener(){
                        @Override
                        public void onClick(View v) {
                                //fill in later
                        	goToOptions();
                        }
                });
                Button btnToMainMenu = (Button)activity.findViewById(R.id.btnToMainMenu);
                btnToMainMenu.setOnClickListener(new OnClickListener(){
                        @Override
                        public void onClick(View v) {
                                //fill in later
                        	goToMain();
                        }
                });
                Button btnToLeaderboardsGameOver = (Button)activity.findViewById(R.id.btnToLeaderboardsGameOver);
                btnToLeaderboardsGameOver.setOnClickListener(new OnClickListener(){
                        @Override
                        public void onClick(View v) {
                                //fill in later
//                        	goToActivity(LeaderBoardActivity.class);
                        	goToLeaderboard();
                        }
                });
        }
        
		public void goToOptions() {
			Intent intent = new Intent(activity,OptionsActivity.class);
			activity.startActivity(intent);
			activity.finish();
		}
		public void goToMain() {
//			Intent intent = new Intent(activity,MainMenuActivity.class);
//			activity.startActivity(intent);
			activity.finish();
		}
		public void goToLeaderboard() {
//			goToActivity(LeaderBoardActivity.class);
			Intent intent = new Intent(activity,LeaderBoardActivity.class);
			activity.startActivity(intent);
			activity.finish();
		}
}