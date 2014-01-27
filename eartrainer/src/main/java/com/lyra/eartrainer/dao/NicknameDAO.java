package com.lyra.eartrainer.dao;

import com.lyra.eartrainer.dao.client.ClientDAOException;
import com.lyra.eartrainer.dto.NicknameDTO;

public interface NicknameDAO extends LyraDAO {
	public String createNickname(NicknameDTO nicknameDTO) throws ClientDAOException;
	public NicknameDTO readNickname(String nameOrId) throws ClientDAOException;
}
