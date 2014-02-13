package com.lyra.eartrainer.dao;

import com.lyra.eartrainer.model.LeaderBoard;
import com.lyra.eartrainer.model.LeaderBoardEntry;

public interface LeaderBoardDao {
	public int addScore(LeaderBoardEntry scoreEntry) throws DaoParseException;
	public LeaderBoardEntry getScore(String identifier) throws DaoParseException;
	public LeaderBoard getScores(int pageNumber) throws DaoParseException;
}
