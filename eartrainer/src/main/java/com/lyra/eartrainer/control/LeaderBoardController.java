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
import com.lyra.eartrainer.dao.LeaderBoardDaoOperations;
import com.lyra.eartrainer.model.LeaderBoard;
import com.lyra.eartrainer.view.LeaderboardView;


public class LeaderBoardController extends Controller {
	private LeaderboardView leaderBoardView;
	private LeaderBoardActivity activity;
	private LeaderBoardDao dao;
	private boolean handlingRequest;
	
	public LeaderBoardController(LeaderBoardActivity leaderBoardActivity){
		super(leaderBoardActivity);
		
		activity = leaderBoardActivity;
		dao = new LeaderBoardDaoImpl();
		handlingRequest = false;
	}
	
	public void initialize(){
		//creating the leaderboard view object
		activity.setContentView(R.layout.activity_leaderboard);
		activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
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
		if(dao.isProcessing() || handlingRequest) return;
		
		handlingRequest = true;
		leaderBoardView.disablePagingButtons();
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
		leaderBoardView.enablePagingButtons();
		
		// Set the page
		leaderBoardView.setPage(dao.getPageNumber());
		if(scores != null){
			leaderBoardView.populateList(scores);
		}
		else {
			System.out.println("Get Scores Failed, returned null scores object!");
			leaderBoardView.showResultsError();
		}
		updatePagingButtons(false);
		handlingRequest = false;
	}
	
	private void getScoreFailed(DaoErrorInfo errorInfo){
		leaderBoardView.stopSpinner();
		leaderBoardView.enablePagingButtons();
		
		if(errorInfo != null && errorInfo.getMessage().equals("No Results")){
			updatePagingButtons(true);
			handlingRequest = false;
			return;
		}
		else if(errorInfo != null && errorInfo.getEx() != null){
			errorInfo.getEx().printStackTrace();
		}
		
		System.out.println("Get Scores Failed!");
		leaderBoardView.showResultsError();
		updatePagingButtons(false);
		handlingRequest = false;
	}
	
	private void updatePagingButtons(boolean emptyResults){
		if(dao.getPageNumber() == 1){
			leaderBoardView.enablePreviousButton(false);
		}
		
		if(emptyResults){
			leaderBoardView.enableNextButton(false);
			if(dao.getPreviousOperation() == LeaderBoardDaoOperations.NEXT_PAGE){
				int page = dao.getPageNumber();
				if(page < 0) 
					page = 0;
				
				leaderBoardView.setPage(page);
			}
		}
	}
}
