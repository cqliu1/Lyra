package com.lyra.eartrainer.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.lyra.eartrainer.control.LyraView;
import com.lyra.eartrainer.model.LeaderBoard;
import com.lyra.eartrainer.model.LeaderBoardEntry;
import com.lyra.eartrainer.R;

public class LeaderboardView extends LyraView {
	private ProgressDialog spinner;
	private Button nextBtn;
	private Button prevBtn;
	
	public LeaderboardView(Activity activity) {
		super(activity);
		nextBtn = (Button)activity.findViewById(R.id.leaderboardNextPage);
		prevBtn = (Button)activity.findViewById(R.id.leaderboardPreviousPage);
	}
	
	public void setPage(int pageNumber) {
		TextView pageText = (TextView) activity.findViewById(R.id.leaderboardPageText);
		pageText.setText("Page " + pageNumber);
	}
	
	public void populateList(LeaderBoard board){
		try {
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
				col1.setTextSize(16);
				col1.setGravity(0x11); //center
				col1.setLayoutParams(col1Params);
				
				TextView col2 = new TextView(activity);
				col2.setText(entry.getNickname()+ "");
				col2.setTextSize(16);
				col2.setLayoutParams(col2Params);
				
				TextView col3 = new TextView(activity);
				col3.setText(entry.getScore() + "");
				col3.setTextSize(16);
				col3.setLayoutParams(col3Params);
				
				scoreRow.addView(col1);
				scoreRow.addView(col2);
				scoreRow.addView(col3);
				
				scoreTable.addView(scoreRow);
			}
		}
		catch(Exception e){
			System.out.println("There was a problem while displaying the leaderboard results");
			showResultsError();
			e.printStackTrace();
		}
	}
	
	public void showResultsError(){
		//move this into a method in the view
		Toast.makeText(activity, "There was a problem while displaying the leaderboard results. Please try again later.", Toast.LENGTH_SHORT).show();
	}
	
	public void enableNextButton(boolean enableIt){
		nextBtn.setEnabled(enableIt);
	}
	
	public void enablePreviousButton(boolean enableIt){
		prevBtn.setEnabled(enableIt);
	}
	
	public void disablePagingButtons(){
		enablePreviousButton(false);
		enableNextButton(false);
	}
	
	public void enablePagingButtons(){
		enablePreviousButton(true);
		enableNextButton(true);
	}
}
