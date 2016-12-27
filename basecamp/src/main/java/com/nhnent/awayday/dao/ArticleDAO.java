package com.nhnent.awayday.dao;

import java.sql.SQLException;
import java.util.List;

import com.nhnent.awayday.dto.ArticleDTO;

public interface ArticleDAO {
	public void insertArticle(ArticleDTO article) throws SQLException;
	public List<ArticleDTO> selectAllArticle();
	public String selectArticlePassword(int id) throws SQLException;
	public void  updateArticle(ArticleDTO article);
	public List<ArticleDTO> selectAllUpdatedArticle();
}
