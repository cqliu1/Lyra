package com.lyra.eartrainer.dao;

import com.lyra.eartrainer.model.LeaderBoard;
import com.lyra.eartrainer.model.LeaderBoardEntry;

public interface LeaderBoardDao {
	// Returns id of added score
	public int addScore(LeaderBoardEntry scoreEntry) throws DaoParseException;
	
	// Returns a Leaderboard with scores from the page
	public LeaderBoard getScores(int pageNumber) throws DaoParseException;

	// Returns a Leaderboard with scores for the next page
	public LeaderBoard getNextPage() throws DaoParseException;
	
	// Returns a leaderboard with scores for the previous page
	public LeaderBoard getPrevPage() throws DaoParseException;
}
