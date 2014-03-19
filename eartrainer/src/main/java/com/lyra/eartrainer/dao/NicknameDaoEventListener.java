package com.lyra.eartrainer.dao;

import com.lyra.eartrainer.model.Nickname;

public abstract class NicknameDaoEventListener {
	public void onStoreNicknameSuccess(Nickname nickName){}
	public void onStoreNicknameError(DaoErrorInfo errorObject){}
}
