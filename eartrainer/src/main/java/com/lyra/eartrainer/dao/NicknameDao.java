package com.lyra.eartrainer.dao;

import java.io.File;

import com.lyra.eartrainer.model.Nickname;

public interface NicknameDao {
	public Nickname storeNickname(File dir, String nickName) throws DuplicateNicknameException;
	public boolean nicknameExists(File dir);
}
