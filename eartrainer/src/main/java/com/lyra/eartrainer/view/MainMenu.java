package com.lyra.eartrainer.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.lyra.eartrainer.MainMenuActivity;
import com.lyra.eartrainer.R;

public class MainMenu extends View {
        private Button settings;
        private Button game;
        private Button leaderboard;

        public MainMenu(Context con, AttributeSet attrs) {
        	super(con,attrs);
            this.game = (Button) findViewById(R.id.btnToGame);
            this.settings = (Button) findViewById(R.id.btnToSettings);
            this.leaderboard = (Button) findViewById(R.id.btnToLeaderboards);
        }

        public void showOptions()
        {
        	MainMenuActivity main = (MainMenuActivity) getContext();
        	main.goToOptions();
        }
}