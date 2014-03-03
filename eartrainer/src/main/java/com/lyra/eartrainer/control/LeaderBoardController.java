package com.lyra.eartrainer.control;

import com.lyra.eartrainer.LeaderBoardActivity;
import com.lyra.eartrainer.view.LeaderboardView;

public class LeaderBoardController extends Controller {
	private LeaderboardView leaderBoardView;
	public LeaderBoardController(LeaderBoardActivity leaderBoardActivity){
		super(leaderBoardActivity);
	}
	
	public void initialize(){
		//creating the leaderboard view object
		leaderBoardView = new LeaderboardView();
		
	}
}
