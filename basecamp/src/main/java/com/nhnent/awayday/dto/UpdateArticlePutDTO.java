package com.nhnent.awayday.dto;

public class UpdateArticlePutDTO {
	private String password;
	private String content;
	
	public String getContent() {
		return content;
	}
	public String getPassword() {
		return password;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
