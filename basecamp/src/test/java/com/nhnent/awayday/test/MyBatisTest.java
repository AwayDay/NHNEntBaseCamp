package com.nhnent.awayday.test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nhnent.awayday.JavaSecurity;
import com.nhnent.awayday.JavaSecuritySHA256;
import com.nhnent.awayday.dao.ArticleDAO;
import com.nhnent.awayday.dto.ArticleDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/test-context.xml")
public class MyBatisTest {
	//@Resource(name = "ArticleDAOService")
	@Autowired
    private ArticleDAO articleDAO;

	@Test
	public void test() {
		JavaSecurity js = new JavaSecuritySHA256();
		ArticleDTO article = new ArticleDTO();
		article.setEmail("junit@test.com");
		article.setPassword(js.getEncrypt("test", "test"));
		article.setContent("!! TEST !!");
		try {
			articleDAO.insertArticle(article);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		fail("Not yet implemented");
	}

}
