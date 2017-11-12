package controllers;

import javax.servlet.http.HttpSession;

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
	public ModelAndView splash(HttpSession session) {
		User user =(User) session.getAttribute("user");
		ModelAndView mv = new ModelAndView("splash"); 
		if(user != null && user.isAdmin()) {
			mv.addObject("admin", user.getUserName()); 
			return mv;
		}
		mv.addObject("admin", "Admin log-in");
		return mv; 
	}
	@RequestMapping(path="browse.do", method=RequestMethod.GET)
	public String browse() {
		return "browse"; 
	}
	@RequestMapping(path="admin.do", method=RequestMethod.GET)
	public String admin(HttpSession session) {
		User user =(User) session.getAttribute("user");
		if(user != null && user.isAdmin()) {
			return"admin";
		}
		return "adminLogIn"; 
	}
}
