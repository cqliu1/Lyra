package com.lyra.eartrainer.dao;

import com.lyra.eartrainer.dao.client.ClientDAOException;
import com.lyra.eartrainer.dto.LeaderBoardDTO;
import com.lyra.eartrainer.dto.LoaderBoardScoreList;

public interface LeaderBoardDAO extends LyraDAO {
	public void addScore(LeaderBoardDTO leaderboardScoreObject) throws ClientDAOException;
	public LeaderBoardDTO getScore(String identifier) throws ClientDAOException;
	public LoaderBoardScoreList getAllScores() throws ClientDAOException;
}
