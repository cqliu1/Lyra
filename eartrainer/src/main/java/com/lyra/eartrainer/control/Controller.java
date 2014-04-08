package com.lyra.eartrainer.control;

import java.sql.Timestamp;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;

import com.lyra.eartrainer.dao.DaoErrorInfo;
import com.lyra.eartrainer.dao.DaoParseException;
import com.lyra.eartrainer.dao.LeaderBoardDao;
import com.lyra.eartrainer.dao.LeaderBoardDaoEventListener;
import com.lyra.eartrainer.dao.LeaderBoardDaoImpl;
import com.lyra.eartrainer.model.GamePlay;
import com.lyra.eartrainer.model.LeaderBoardEntry;
import com.lyra.eartrainer.model.globals.Modes;

public class Controller {
	protected Activity activity;
	protected LeaderBoardDao leaderBoardDao;
	
	private Controller(){}
	
	public Controller(Activity activity){
		this.activity = activity;
	}
	
	protected void goToActivity(Class<?> activityType){
		//activity.finish();
		Intent intent = new Intent(activity, activityType);
		activity.startActivity(intent);
	}
	
	protected void initializeLeaderBoardDao(final LyraView lyraView){
		leaderBoardDao = new LeaderBoardDaoImpl();
		leaderBoardDao.addEventListener(new LeaderBoardDaoEventListener(){
			public void onSaveScoreSuccess(final Integer recordId){
				activity.runOnUiThread(new Runnable(){
					public void run(){
						saveScoreSuccess(recordId, lyraView);
					}
				});
			}
			public void onSaveScoreFailure(final DaoErrorInfo errorObject){
				activity.runOnUiThread(new Runnable(){
					public void run(){
						saveScoreFailed(errorObject, lyraView);
					}
				});
			}
		});
	}
	
	public void addScore(LyraView lyraView){
		GamePlay game = GamePlay.instance();
		if(game.getMode() != Modes.CHALLENGE){
			return;
		}
		if(game.getScore() <= 0) {
			return;
		}
		
		Timestamp ts = new Timestamp(new Date().getTime());
		
		LeaderBoardEntry entry = new LeaderBoardEntry();
		entry.setDate(ts + "");
		entry.setUser_id(game.getNickname().getId());
		entry.setDifficulty(game.getDifficulty());
		entry.setInstrument(game.getInstrumentType());
		entry.setGame(game.getMode());
		entry.setScore(game.getScore());
		
		lyraView.startSpinner(activity, "Saving Score", "Please wait...");
		
		try {
			leaderBoardDao.addScore(entry);
		} catch(DaoParseException dpe){
			System.out.println("Failed to save Leaderboard score entry.");
			dpe.printStackTrace();
		}
	}
	
	public void saveScoreSuccess(Integer recordId, LyraView lyraView){
		lyraView.stopSpinner();
		System.out.println("Successfully saved score!");
	}
	
	public void saveScoreFailed(DaoErrorInfo errorInfo, LyraView lyraView){
		lyraView.stopSpinner();
		System.out.println("Failed to save score!");
		if(errorInfo != null && errorInfo.getEx() != null){
			errorInfo.getEx().printStackTrace();
		}
	}
}
