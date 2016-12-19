package com.nhnent.awayday.dao;

import java.util.List;

import com.nhnent.awayday.dto.ArticleDTO;

public interface ArticleDAO {
	public void insertArticle(ArticleDTO article);
	public List<ArticleDTO> selectAllArticle();
}
