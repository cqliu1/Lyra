package com.lyra.eartrainer.dao;

import java.util.ArrayList;

import com.lyra.eartrainer.dao.exception.BadRequestException;
import com.lyra.eartrainer.dao.exception.ConflictException;
import com.lyra.eartrainer.dao.exception.DaoParseException;
import com.lyra.eartrainer.dao.exception.NotFoundException;
import com.lyra.eartrainer.dao.exception.ServerErrorException;

public interface LyraDAO {
	public String create(Object transferObject) throws DaoParseException, ConflictException, BadRequestException, ServerErrorException;
	public Object read(String identifier, Class<?> transferObjectClass) throws DaoParseException, NotFoundException, ServerErrorException;
	//public ArrayList readAll(int pageNumber, Class<?> transferObjectClass) throws DaoParseException, NotFoundException, ServerErrorException;
}
