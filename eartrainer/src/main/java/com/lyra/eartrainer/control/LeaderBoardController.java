package com.lyra.eartrainer.control;

import android.content.pm.ActivityInfo;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.lyra.eartrainer.LeaderBoardActivity;
import com.lyra.eartrainer.R;
import com.lyra.eartrainer.dao.DaoErrorInfo;
import com.lyra.eartrainer.dao.LeaderBoardDao;
import com.lyra.eartrainer.dao.LeaderBoardDaoEventListener;
import com.lyra.eartrainer.dao.LeaderBoardDaoImpl;
import com.lyra.eartrainer.model.LeaderBoard;
import com.lyra.eartrainer.view.LeaderboardView;


public class LeaderBoardController extends Controller {
	private LeaderboardView leaderBoardView;
	private LeaderBoardActivity activity;
	private LeaderBoardDao dao;
	
	public LeaderBoardController(LeaderBoardActivity leaderBoardActivity){
		super(leaderBoardActivity);
		
		activity = leaderBoardActivity;
		dao = new LeaderBoardDaoImpl();
	}
	
	public void initialize(){
		//creating the leaderboard view object
		activity.setContentView(R.layout.activity_leaderboard);
		activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		leaderBoardView = new LeaderboardView(activity);
		fetchScores(0);
		attachEvents();
	}
	
	private void attachEvents(){
		Button prevBtn = (Button)activity.findViewById(R.id.leaderboardPreviousPage);
		prevBtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				fetchScores(1);
			}
		});
		
		Button nextBtn = (Button)activity.findViewById(R.id.leaderboardNextPage);
		nextBtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				fetchScores(2);
			}
		});
		
		//DAO Events
		dao.addEventListener(new LeaderBoardDaoEventListener(){
			public void onGetScoreSuccess(final LeaderBoard leaderBoard){
				activity.runOnUiThread(new Runnable(){
					public void run(){
						getScoreSuccess(leaderBoard);
					}
				});
			}
			public void onGetScoreFailure(final DaoErrorInfo errorObject){
				activity.runOnUiThread(new Runnable(){
					public void run(){
						getScoreFailed(errorObject);
					}
				});
			}
			public void onError(final DaoErrorInfo errorObject){
				activity.runOnUiThread(new Runnable(){
					public void run(){
						leaderBoardView.stopSpinner();
						System.out.println("An error has occurred.");
						if(errorObject != null && errorObject.getEx() != null){
							errorObject.getEx().printStackTrace();
						}
					}
				});
			}
		});
	}
	
	private void fetchScores(int option){
		leaderBoardView.startSpinner(activity, "Loading Scores", "Please wait...");
		if(option == 1)
			dao.getPrevPage();
		else if(option == 2)
			dao.getNextPage();
		else 
			dao.getScores(1);
	}
	
	private void getScoreSuccess(LeaderBoard scores){
		leaderBoardView.stopSpinner();
		// Set the page
		leaderBoardView.setPage(dao.getPageNumber());
		if(scores != null){
			leaderBoardView.populateList(scores);
		}
		else {
			System.out.println("Get Scores Failed, returned null scores object!");
			showError();
		}
	}
	
	private void getScoreFailed(DaoErrorInfo errorInfo){
		leaderBoardView.stopSpinner();
		System.out.println("Get Scores Failed!");
		if(errorInfo != null){
			errorInfo.getEx().printStackTrace();
		}
		showError();
	}

	private void showError(){
		//move this into a method in the view
		Toast.makeText(activity, "There was a problem while displaying the leaderboard results. Please try again later.", Toast.LENGTH_SHORT).show();
	}
}
