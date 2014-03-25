package com.lyra.eartrainer.dao;

public class DuplicateNicknameException extends Exception {
	private static final long serialVersionUID = 1L;

	public DuplicateNicknameException(String message){
		super(message);
	}
}
