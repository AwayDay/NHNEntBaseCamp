package com.nhnent.awayday;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nhnent.awayday.dao.ArticleDAO;
import com.nhnent.awayday.dto.ArticleDTO;

@RestController
public class MyRestController {
	
	@Autowired
	private ArticleDAO articleDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(MyRestController.class);
	
	@RequestMapping(value = "/article", method = RequestMethod.POST)
	public ResponseEntity<?> newArticle(@RequestParam("email") String email,@RequestParam("password") String password, @RequestParam("content") String content) {
		logger.info("hello POST!");
		logger.info("Your E-mail addr : {}", email);
		logger.info("Your password : {}", password);
		logger.info("Your text : {}", content);
		
		ArticleDTO article = new ArticleDTO();
		
		article.setEmail(email);
		article.setPassword(password);
		article.setContent(content);
		
		articleDAO.insertArticle(article);
		
		return new ResponseEntity<>("OK", HttpStatus.OK);	
	}
}
