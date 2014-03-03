package com.lyra.eartrainer.control;

import android.widget.Toast;

import com.lyra.eartrainer.LeaderBoardActivity;
import com.lyra.eartrainer.R;
import com.lyra.eartrainer.dao.DaoParseException;
import com.lyra.eartrainer.dao.LeaderBoardDao;
import com.lyra.eartrainer.dao.LeaderBoardDaoImpl;
import com.lyra.eartrainer.model.LeaderBoard;
import com.lyra.eartrainer.model.LeaderBoardEntry;
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
		leaderBoardView = new LeaderboardView(activity);
		fetchScores();
	}
	
	private void attachEvents(){
		//TODO add event handlers for buttons
	}
	
	private void fetchScores(){
		LeaderBoard scores = null;
		try {
			scores = dao.getScores(1);
		} catch(DaoParseException dpe){
			showError();
			dpe.printStackTrace();
		}
		
		if(scores != null){
			leaderBoardView.populateList(scores);
		}
		else {
			showError();
		}
	}
	
	private void showError(){
		//move this into a method in the view
		Toast.makeText(activity, "There was a problem while displaying the leaderboard results. Please try again later.", Toast.LENGTH_SHORT).show();
	}
}
