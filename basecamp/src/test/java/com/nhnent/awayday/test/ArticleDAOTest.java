package com.nhnent.awayday.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.SQLException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.nhnent.awayday.dao.ArticleDAO;
import com.nhnent.awayday.dao.impl.ArticleDAOService;
import com.nhnent.awayday.dto.ArticleDTO;

public class ArticleDAOTest {
	private ArticleDAO articleDAO;
	
	@Test
	public void testInsertArticle(){
		articleDAO = new ArticleDAOService();
		ArticleDTO article = new ArticleDTO();
		article.setEmail("test@test.com");
		article.setPassword("test");
		article.setContent("!!test!!");
		try {
			articleDAO.insertArticle(article);
			ArticleDTO getNew = articleDAO.selectAllArticle().stream()
										.max((p1, p2) -> Integer.compare(p1.getId(), p2.getId()))
										.get();
			assertEquals(article.getContent(), getNew.getContent());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
}
