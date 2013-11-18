package com.lyra.eartrainer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import com.lyra.eartrainer.control.GameOverController;

public class GameOverActivity extends Activity {
        
        @Override
        public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_gameover);
                
                GameOverController nController = new GameOverController(this);
                nController.initialize();
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
                // Inflate the menu; this adds items to the action bar if it is present.
                getMenuInflater().inflate(com.lyra.eartrainer.R.menu.main, menu);
                return true;
        }

		public void goToOptions() {
			// TODO Auto-generated method stub
			Intent intent = new Intent(this,OptionsActivity.class);
			startActivity(intent);
			finish();
		}
		public void goToMain() {
			// TODO Auto-generated method stub
			Intent intent = new Intent(this,MainMenuActivity.class);
			startActivity(intent);
			finish();
		}
		public void goToLeaderboard() {
			// TODO Auto-generated method stub
			// direct to leaderboard
		}

}