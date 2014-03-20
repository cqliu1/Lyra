package com.lyra.eartrainer.view;

import com.lyra.eartrainer.R;
import com.lyra.eartrainer.model.GamePlay;

import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;

public class MainMenu{
		private TextView nickname;
        private Button settings;
        private Button game;
        private Button leaderboard;
        private TextView textDescription;

        public MainMenu(Activity activity) {
        	textDescription = (TextView)activity.findViewById(R.id.textDescription);
        	showNickname();
        }
        
        private void showNickname(){
        	textDescription.setText("Welcome, " + GamePlay.instance().getNickname().getName() + "!");
        }
}