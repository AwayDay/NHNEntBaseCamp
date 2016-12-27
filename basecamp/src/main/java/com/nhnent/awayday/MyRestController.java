package com.nhnent.awayday;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@RequestMapping(value = "/article", method = RequestMethod.POST, produces="application/json; charset=utf8")
	public ResponseEntity<?> newArticle(@RequestParam("email") String email,@RequestParam("password") String password, @RequestParam("content") String content) {
		logger.info("hello POST!");
		
		logger.info("Your E-mail addr : {}", email);
		logger.info("Your password : {}", password);
		logger.info("Your text : {}", content);
			
		if (isCorrectForm(email, password, content)){
			EmailCheck ec = (e) -> {
				String list[] = e.split("@");
				return ((list.length) == 2)&&(list[list.length-1].length() != 0)&&(list[0].length() != 0);
			};
			if (ec.isEmail(email)) {
				ArticleDTO article = new ArticleDTO();
				article.setEmail(email);
				article.setPassword(password);
				article.setContent(content);
				try {
					articleDAO.insertArticle(article);
				} catch (SQLException e) {
					e.printStackTrace();
					return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
				}
				return new ResponseEntity<>("OK", HttpStatus.OK);	
			} else {
				return new ResponseEntity<>("올바른 이메일 주소를 입력하세요.", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("비어있는 칸이 없어야 합니다.", HttpStatus.BAD_REQUEST);
		}
	}
	
	public boolean isCorrectForm(String email, String password, String content){
		return !email.isEmpty() && !password.isEmpty() && !content.isEmpty();
	}
	
	public boolean isEmail(String addr) {
		EmailCheck ec = (e) -> {
			String list[] = e.split("@");
			return ((!addr.isEmpty())&&(list.length) == 2)&&(list[list.length-1].length() != 0)&&(list[0].length() != 0);
		};
		return ec.isEmail(addr);
	}
	
	@RequestMapping(value = "/article/{id}", method = RequestMethod.PUT, produces="application/json; charset=utf8")
	public ResponseEntity<?> updateArticle(@PathVariable int id, @RequestBody ArticleDTO articleDTO){
		logger.info("hello PUT!");
		logger.info("id : {}", id);
		logger.info("Your password : {}", articleDTO.getPassword());
		logger.info("Your text : {}", articleDTO.getContent());
		
		//logger.info("article's password : {}", articleDAO.selectArticlePassword(id));
		if(isCorrectPassword(id, articleDTO.getPassword())){
			articleDTO.setId(id);
			
			articleDAO.updateArticle(articleDTO);
			return new ResponseEntity<>("성공", HttpStatus.OK);	
		} else {
			return new ResponseEntity<>("비밀번호가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED);	
		}
	}
	
	public boolean isCorrectPassword(int id, String pw){
		if(pw.equals(articleDAO.selectArticlePassword(id))){
			return true;
		}
		return false;
	}
}
