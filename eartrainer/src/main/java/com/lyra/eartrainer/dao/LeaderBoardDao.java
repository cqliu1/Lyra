package com.lyra.eartrainer.dao;

import com.lyra.eartrainer.model.LeaderBoardEntry;

public interface LeaderBoardDao {
	// Saves score to leaderboard
	public void addScore(LeaderBoardEntry scoreEntry) throws DaoParseException;
	
	// Pulls a Leaderboard with scores from the page
	public void getScores(int pageNumber);

	// Pulls a Leaderboard with scores for the next page
	public void getNextPage();
	
	// Pulls a leaderboard with scores for the previous page
	public void getPrevPage();

	// Returns the page number that we are currently on
	public int getPageNumber();
	
	// Adds event handler(s) that react to the varying events 
	public void addEventListener(LeaderBoardDaoEventListener listener);
	
	// Removes an event handler
	public void removeListener(LeaderBoardDaoEventListener listener);
}
