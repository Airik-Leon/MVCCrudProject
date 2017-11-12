package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import data.PostDAO;
import data.User;

@Controller
public class BlogController {
	@Autowired
	PostDAO dao; 
	
	//Routing to pages
	@RequestMapping(path="splash.do", method=RequestMethod.GET)
	public ModelAndView splash() {
		ModelAndView mv = new ModelAndView(); 
		mv.setViewName("splash");
		return mv; 
	}
	@RequestMapping(path="browse.do", method=RequestMethod.GET)
	public String browse() {
		return "browse"; 
	}
	@RequestMapping(path="admin.do", method=RequestMethod.GET)
	public String admin() {
		return "adminLogIn"; 
	}
}
