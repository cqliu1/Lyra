package com.lyra.eartrainer.dao;

public class DaoErrorInfo {
	private int statusCode;
	private String message;
	private Exception ex;
	
	public DaoErrorInfo(int statusCode, String message, Exception ex){
		this.statusCode = statusCode;
		this.message = message;
		this.ex = ex;
	}
	
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Exception getEx() {
		return ex;
	}
	public void setEx(Exception ex) {
		this.ex = ex;
	}
}
