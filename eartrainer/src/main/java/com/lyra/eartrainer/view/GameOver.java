package com.lyra.eartrainer.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.lyra.eartrainer.GameOverActivity;
import com.lyra.eartrainer.R;

public class GameOver extends View{
        private Button replay;
        private Button mainmenu;
        private Button leaderboard;

        public GameOver(Context con, AttributeSet attrs) {
        	super(con,attrs);
            this.replay = (Button) findViewById(R.id.btnToReplay);
            this.mainmenu = (Button) findViewById(R.id.btnToMainMenu);
            this.leaderboard = (Button) findViewById(R.id.btnToLeaderboardsGameOver);

        }

        public void restartGame(){
        	GameOverActivity gameOver = (GameOverActivity) getContext();
        	gameOver.goToOptions();
        }

		public void showMain() {
			// TODO Auto-generated method stub
			GameOverActivity gameOver = (GameOverActivity) getContext();
        	gameOver.goToMain();
		}

		public void showLeaderboard() {
			// TODO Auto-generated method stub
			GameOverActivity gameOver = (GameOverActivity) getContext();
        	gameOver.goToLeaderboard();
		}
}