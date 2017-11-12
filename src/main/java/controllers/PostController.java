package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import data.PostDAO;

@Controller
public class PostController {
	@Autowired
	PostDAO dao; 
	
	@RequestMapping("goToCreatePost.do")
	public ModelAndView goToCreatePost() {
		ModelAndView mv = new ModelAndView("createPost"); 
		return mv; 
	}
}
