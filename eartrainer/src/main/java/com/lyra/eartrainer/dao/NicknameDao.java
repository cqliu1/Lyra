package com.lyra.eartrainer.dao;

import com.lyra.eartrainer.model.Nickname;

public interface NicknameDao {
	//Stores nickname in leaderboard data service and caches it locally
	public void storeNickname(String nickName);
	
	//Gets the locally cached nickname
	public Nickname getLocalNickname() throws DaoParseException;
	
	// Adds event handler(s) that react to the varying events 
	public void addEventListener(NicknameDaoEventListener listener);
	
	// Removes an event handler
	public void removeListener(NicknameDaoEventListener listener);
}
