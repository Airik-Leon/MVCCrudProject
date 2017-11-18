package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import data.BlogDAO;
import data.Post;

@Controller
public class BrowseController {
	@Autowired
	BlogDAO dao; 
	
	@RequestMapping("goToArticles.do")
	public ModelAndView articles() {
		ModelAndView mv = new ModelAndView("Articles"); 
		List<Post> posts = dao.getPostsByCategory("Articles");
		for (Post post : posts) {
			post.setReplies(dao.getPostReplies(post));
		}
		mv.addObject("posts", posts); 
		return mv; 
	}
	@RequestMapping("goToAfterThoughts.do")
	public ModelAndView afterThoughts() {
		ModelAndView mv = new ModelAndView("AfterThoughts"); 
		List<Post> posts = dao.getPostsByCategory("AfterThoughts");
		for (Post post : posts) {
			post.setReplies(dao.getPostReplies(post));
		}
		mv.addObject("posts", posts); 
		return mv; 
	}
	@RequestMapping("goToPhotography.do")
	public ModelAndView photorgraphy() {
		ModelAndView mv = new ModelAndView("Photography"); 
		List<Post> posts = dao.getPostsByCategory("Photography");
		for (Post post : posts) {
			post.setReplies(dao.getPostReplies(post));
		}
		mv.addObject("posts", posts); 
		return mv; 
	}
	@RequestMapping("goToAllMessages.do")
	public ModelAndView allMessages() {
		ModelAndView mv = new ModelAndView("allMessages");
		List<Post> posts = dao.getPosts(); 
		for (Post post : posts) {
			post.setReplies(dao.getPostReplies(post));
		}
		mv.addObject("posts", posts); 
		return mv; 
	}
}
