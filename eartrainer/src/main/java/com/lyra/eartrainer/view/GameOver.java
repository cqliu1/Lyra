package com.lyra.eartrainer.view;

import android.app.Activity;
import android.widget.Button;

import com.lyra.eartrainer.R;

public class GameOver {
	private Button replay;
	private Button mainmenu;
	private Button leaderboard;

	public GameOver(Activity activity) {

		this.replay = (Button) activity.findViewById(R.id.btnToReplay);
		this.mainmenu = (Button) activity.findViewById(R.id.btnToMainMenu);
		this.leaderboard = (Button) activity
				.findViewById(R.id.btnToLeaderboardsGameOver);

	}

}