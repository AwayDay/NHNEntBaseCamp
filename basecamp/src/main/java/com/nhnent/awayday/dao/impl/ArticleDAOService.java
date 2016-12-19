package com.nhnent.awayday.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nhnent.awayday.dao.ArticleDAO;
import com.nhnent.awayday.dto.ArticleDTO;

@Repository	
public class ArticleDAOService implements ArticleDAO {
	@Autowired
    private SqlSession sqlSession;

	@Override
	public void insertArticle(ArticleDTO article) {
		// TODO Auto-generated method stub
		sqlSession.insert("articleSQL.insertArticle", article);
	}

}
