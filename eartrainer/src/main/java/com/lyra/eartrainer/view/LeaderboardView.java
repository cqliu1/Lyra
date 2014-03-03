package com.lyra.eartrainer.view;

import android.app.Activity;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.lyra.eartrainer.model.LeaderBoard;
import com.lyra.eartrainer.model.LeaderBoardEntry;
import com.lyra.eartrainer.R;

public class LeaderboardView {
	
	private LeaderBoard board;
	private Activity activity;
	
	public LeaderboardView(Activity activity, LeaderBoard board) {
		this.board = board;
	}
	
	public void populateList(){
		// Get reference to the layout table
		TableLayout scoreTable = (TableLayout) activity.findViewById(R.id.leaderboardTable);
		
		// Get the header row
		TableRow header = (TableRow) scoreTable.findViewById(R.id.leaderboardTableHeader);
		
		// Remove all children from the scoreTable
		scoreTable.removeAllViews();
		
		// Add header back in
		scoreTable.addView(header);
		
		// Add new row for each in board
		for(LeaderBoardEntry entry : board.getItems()) {
			TableRow scoreRow = new TableRow();
			
		}
	}
}
