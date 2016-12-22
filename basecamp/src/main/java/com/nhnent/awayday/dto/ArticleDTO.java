package com.nhnent.awayday.dto;

import java.sql.Timestamp;

public class ArticleDTO {
	private int id;
	private String email;
	private String password;
	private String content;
	private Timestamp createdDate;
	private Timestamp updatedDate;
	
	public int getId() {
		return id;
	}
	public String getContent() {
		return content;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public Timestamp getUpdatedDate() {
		return updatedDate;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}
	
}
