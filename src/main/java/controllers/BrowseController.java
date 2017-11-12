package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import data.PostDAO;

@Controller
public class BrowseController {
	@Autowired
	PostDAO dao; 
	
	@RequestMapping("goToArticles.do")
	public ModelAndView articles() {
		ModelAndView mv = new ModelAndView("Articles"); 
		mv.addObject("posts", dao.getPostsByCategory("Articles")); 
		return mv; 
	}
	@RequestMapping("goToAfterThoughts.do")
	public ModelAndView afterThoughts() {
		ModelAndView mv = new ModelAndView("AfterThoughts"); 
		mv.addObject("posts", dao.getPostsByCategory("AfterThoughts")); 
		return mv; 
	}
	@RequestMapping("goToPhotography.do")
	public ModelAndView photorgraphy() {
		ModelAndView mv = new ModelAndView("Photography"); 
		mv.addObject("posts", dao.getPostsByCategory("Photography")); 
		return mv; 
	}
	@RequestMapping("goToAllMessages.do")
	public ModelAndView allMessages() {
		ModelAndView mv = new ModelAndView("allMessages"); 
		mv.addObject("posts", dao.getPosts()); 
		return mv; 
	}
}
