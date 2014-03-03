package com.lyra.eartrainer.control;

import android.widget.Toast;

import com.lyra.eartrainer.LeaderBoardActivity;
import com.lyra.eartrainer.dao.DaoParseException;
import com.lyra.eartrainer.dao.LeaderBoardDao;
import com.lyra.eartrainer.dao.LeaderBoardDaoImpl;
import com.lyra.eartrainer.model.LeaderBoard;
import com.lyra.eartrainer.view.LeaderboardView;


public class LeaderBoardController extends Controller {
	private LeaderboardView leaderBoardView;
	private LeaderBoardActivity activity;
	private LeaderBoardDao dao;
	private int scorePage;
	
	public LeaderBoardController(LeaderBoardActivity leaderBoardActivity){
		super(leaderBoardActivity);
		
		activity = leaderBoardActivity;
		dao = new LeaderBoardDaoImpl();
		scorePage = 0;
	}
	
	public void initialize(){
		//creating the leaderboard view object
		leaderBoardView = new LeaderboardView(activity);
		fetchScores();
	}
	
	private void attachEvents(){
		//TODO add event handlers for buttons
	}
	
	private void fetchScores(){
		LeaderBoard scores = null;
		try {
			scores = dao.getScores(scorePage);
			scorePage++;
		} catch(DaoParseException dpe){
			showError();
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
