package com.lyra.eartrainer.dao;

import com.lyra.eartrainer.model.LeaderBoard;

public abstract class LeaderBoardDaoEventListener {
	public void onSaveScoreSuccess(Integer recordId){}				//succeeded in saving a score
	public void onSaveScoreFailure(DaoErrorInfo errorObject){} 		//error when saving a score
	public void onGetScoreSuccess(LeaderBoard leaderBoard){} 		//succeeded in getting scores
	public void onGetScoreFailure(DaoErrorInfo errorObject){} 		//error when getting scores
	public void onError(DaoErrorInfo errorObject){} 				//general error
}
