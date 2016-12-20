package com.nhnent.awayday;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.nhnent.awayday.dao.ArticleDAO;
import com.nhnent.awayday.dto.ArticleDTO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private ArticleDAO articleDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		model.addAttribute("allArticle", articleDAO.selectAllArticle());
		
		return "home";
	}
	
	@RequestMapping(value = "/newarticle", method = RequestMethod.POST)
	public ModelAndView newArticle(@RequestParam("email") String email,@RequestParam("password") String password, @RequestParam("content") String content) {
		logger.info("hello POST!");
		//logger.info("Your E-mail addr : {}", email);
		//logger.info("Your password : {}", password);
		//logger.info("Your text : {}", content);
		ArticleDTO article = new ArticleDTO();
		article.setEmail(email);
		article.setPassword(password);
		article.setContent(content);
		
		articleDAO.insertArticle(article);
		
		/*
		RedirectView rv = new RedirectView("/");
		rv.setExposeModelAttributes(false);
		return new ModelAndView(rv);
		*/
		return new ModelAndView("redirect:/");
	}
	
}
