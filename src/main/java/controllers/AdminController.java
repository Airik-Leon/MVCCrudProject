package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import data.PostDAO;

@Controller
public class AdminController {
	@Autowired
	private PostDAO dao; 
		
	@RequestMapping("log-in.do")
	public ModelAndView crud( @ModelAttribute("loggedIn") boolean log) {
		ModelAndView mv = new ModelAndView(); 
		log = true; 
		mv.addObject("loggedIn", log); 
		mv.setViewName("admin");
		return mv; 
	}
}
