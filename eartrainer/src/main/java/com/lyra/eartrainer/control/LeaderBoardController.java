package com.lyra.eartrainer.control;

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
	
	public void initialize() throws DaoParseException{
		
		LeaderBoard scores = dao.getScores(scorePage);
		scorePage++;
		
		//creating the leaderboard view object
		leaderBoardView = new LeaderboardView(activity, scores);
		
	}
}
