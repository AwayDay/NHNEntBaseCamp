package com.nhnent.awayday;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
		
		if(isEmail(email)){
			ArticleDTO article = new ArticleDTO();
			
			article.setEmail(email);
			article.setPassword(password);
			article.setContent(content);
			
			articleDAO.insertArticle(article);
			
			return new ResponseEntity<>("OK", HttpStatus.OK);	
		} else {
			return new ResponseEntity<>("올바른 이메일 주소를 입력하세요.", HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	private boolean isEmail(String addr) {
		if (addr.split("@").length == 2){
			return true;
		}
		return false;
	}
	
	@RequestMapping(value = "/article/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateArticle(@PathVariable int id, @RequestParam("password") String password){
		return new ResponseEntity<>("OK", HttpStatus.OK);	
	}
}
