package com.lyra.eartrainer.client.exception;

public class ServerErrorException extends Exception {
	private static final long serialVersionUID = 1L;

	public ServerErrorException(String message){
		super(message);
	}
}
