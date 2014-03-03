package com.lyra.eartrainer.view;

import android.app.Activity;
import android.view.ViewGroup.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.lyra.eartrainer.model.LeaderBoard;
import com.lyra.eartrainer.model.LeaderBoardEntry;
import com.lyra.eartrainer.R;

public class LeaderboardView {
	private Activity activity;
	
	public LeaderboardView(Activity activity) {
		this.activity = activity;
	}
	
	public void populateList(LeaderBoard board){
		// Get reference to the layout table
		TableLayout scoreTable = (TableLayout) activity.findViewById(R.id.leaderboardTable);
		
		// Get the header row
		TableRow header = (TableRow) scoreTable.findViewById(R.id.leaderboardTableHeader);
		LayoutParams rowParams = header.getLayoutParams();
		
		// Get the column layouts from the header
		LayoutParams col1Params = header.findViewById(R.id.leaderboardTableHeaderCol1).getLayoutParams();
		LayoutParams col2Params = header.findViewById(R.id.leaderboardTableHeaderCol2).getLayoutParams();
		LayoutParams col3Params = header.findViewById(R.id.leaderboardTableHeaderCol3).getLayoutParams();
		
		// Remove all children from the scoreTable
		scoreTable.removeAllViews();
		
		// Add header back in
		scoreTable.addView(header);
		
		// Add new row for each in board
		for(LeaderBoardEntry entry : board.getItems()) {
			TableRow scoreRow = new TableRow(activity);
			scoreRow.setLayoutParams(rowParams);
			
			TextView col1 = new TextView(activity);
			col1.setText(entry.getId()  + "");
			col1.setLayoutParams(col1Params);
			
			TextView col2 = new TextView(activity);
			col2.setText(entry.getNickname()+ "");
			col2.setLayoutParams(col2Params);
			
			TextView col3 = new TextView(activity);
			col3.setText(entry.getScore() + "");
			col3.setLayoutParams(col3Params);
			
			scoreRow.addView(col1);
			scoreRow.addView(col2);
			scoreRow.addView(col3);
			
			scoreTable.addView(scoreRow);
			
		}
	}
}
