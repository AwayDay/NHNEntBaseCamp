package com.nhnent.awayday.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nhnent.awayday.StringCheck;
import com.nhnent.awayday.dao.ArticleDAO;
import com.nhnent.awayday.dto.ArticleDTO;

@RestController
public class MyRestController {
	
	@Autowired
	private ArticleDAO articleDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(MyRestController.class);
	
	@RequestMapping(value = "/article", method = RequestMethod.POST, produces="application/json; charset=utf8")
	public ResponseEntity<?> newArticle(@RequestBody ArticleDTO articleDTO) {
		logger.info("hello POST!");
		
		logger.info("Your E-mail addr : {}", articleDTO.getEmail());
		logger.info("Your password : {}", articleDTO.getPassword());
		logger.info("Your text : {}", articleDTO.getContent());
		
		return newArticleFactory(articleDTO);
	}
	
	public ResponseEntity<?> newArticleFactory(ArticleDTO articleDTO){
		Map<String, String> map = new HashMap<>();
		if (isCorrectForm(articleDTO.getEmail(), articleDTO.getPassword(), articleDTO.getContent())){
			if (isEmail(articleDTO.getEmail())) {
				try {
					articleDAO.insertArticle(articleDTO);
					map.put("message", "등록 성공");
					return new ResponseEntity<>(map, HttpStatus.CREATED);
				} catch (SQLException e) {
					e.printStackTrace();
					return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			} else {
				return new ResponseEntity<>("올바른 이메일 주소를 입력하세요.", HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>("비어있는 항목이 없어야 합니다.", HttpStatus.BAD_REQUEST);
		}
	}
	
	public boolean isCorrectForm(String email, String password, String content){
		return !email.isEmpty() && !password.isEmpty() && !content.isEmpty();
	}
	
	public boolean isEmail(String addr) {
		String emailFilter = "^[\\w]+[\\-_\\.]*@[\\w]+\\.[a-zA-Z]+$";
		StringCheck ec = (f, e) -> e.matches(f);
		return ec.isCorrectString(emailFilter, addr);
	}
	
	@RequestMapping(value = "/article/{id}", method = RequestMethod.PUT, produces="application/json; charset=utf8")
	public ResponseEntity<?> updateArticle(@PathVariable int id, @RequestBody ArticleDTO articleDTO){
		logger.info("hello PUT!");
		logger.info("id : {}", id);
		logger.info("Your password : {}", articleDTO.getPassword());
		logger.info("Your text : {}", articleDTO.getContent());
		
		Map<String, String> map = new HashMap<>();
		
		try {
			if (!articleDTO.getPassword().isEmpty() && articleDTO.getPassword().equals(articleDAO.selectArticlePassword(id))) {
				articleDTO.setId(id);
				
				articleDAO.updateArticle(articleDTO);
								
				map.put("message", "성공");
				return new ResponseEntity<>(map, HttpStatus.OK);	
			} else {
				//map.put("message", "비밀번호가 일치하지 않습니다.");
				return new ResponseEntity<>("비밀번호가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED);	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//map.put("message", "존재하지 않는 글입니다.");
			return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
		}
	}
}
