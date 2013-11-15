package com.lyra.eartrainer.view;

import android.app.Activity;
import android.widget.Button;

import com.lyra.eartrainer.R;

public class MainMenu {
	private Button settings;
	private Button game;
	private Button leaderboard;

	public MainMenu(Activity activity) {

		this.game = (Button) activity.findViewById(R.id.btnToGame);
		this.settings = (Button) activity.findViewById(R.id.btnToSettings);
		this.leaderboard = (Button) activity
				.findViewById(R.id.btnToLeaderboards);

	}

}
