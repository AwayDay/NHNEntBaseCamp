package com.nhnent.awayday.dto;

import java.sql.Date;

public class ArticleDTO {
	private int id;
	private String email;
	private String password;
	private String content;
	private Date createdDate;
	private Date updatedDate;
	
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
	public Date getCreatedDate() {
		return createdDate;
	}
	public Date getUpdatedDate() {
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
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
}
