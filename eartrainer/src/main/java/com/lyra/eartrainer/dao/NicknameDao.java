package com.lyra.eartrainer.dao;

import com.lyra.eartrainer.model.Nickname;

public interface NicknameDao {
	public void storeNickname(String nickName);
	public Nickname getLocalNickname() throws DaoParseException;
	public void addEventListener(DAOEventListener listener);
	public void removeListener(DAOEventListener listener);
}
