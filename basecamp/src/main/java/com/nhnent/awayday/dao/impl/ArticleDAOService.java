package com.nhnent.awayday.dao.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nhnent.awayday.dao.ArticleDAO;
import com.nhnent.awayday.dto.ArticleDTO;
import com.sun.java.swing.plaf.windows.WindowsTreeUI.CollapsedIcon;

@Repository	
public class ArticleDAOService implements ArticleDAO {
	@Autowired
    private SqlSession sqlSession;

	@Override
	public void insertArticle(ArticleDTO article) {
		// TODO Auto-generated method stub
		sqlSession.insert("articleSQL.insertArticle", article);
	}
	
	@Override
	public List<ArticleDTO> selectAllArticle(){
		return sqlSession.selectList("articleSQL.selectAllArticle");
	}

	@Override
	public String selectArticlePassword(int id) {
		return sqlSession.selectOne("articleSQL.selectAnArticlePassword", id);
	}
	
	@Override
	public void updateArticle(ArticleDTO article) {
		sqlSession.update("articleSQL.updateArticle", article);
	}
	
	@Override
	public List<ArticleDTO> selectAllUpdatedArticle() {
		List<ArticleDTO> updatedArticleList = sqlSession.selectList("articleSQL.selectAllArticle");			
		return updatedArticleList
				.stream()
				.filter(article -> article.getUpdatedDate() != null)
				.collect(Collectors.toList());
	}
}
