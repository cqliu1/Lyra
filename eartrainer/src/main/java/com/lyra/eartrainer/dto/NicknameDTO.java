package com.lyra.eartrainer.dto;

public class NicknameDTO {
	private String nickname;
	private String generatedPassHash;

	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getGeneratedPassHash() {
		return generatedPassHash;
	}
	public void setGeneratedPassHash(String generatedPassHash) {
		this.generatedPassHash = generatedPassHash;
	}
	
}
