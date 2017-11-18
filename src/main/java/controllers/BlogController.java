package controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import data.BlogDAO;
import data.User;

@Controller
public class BlogController {
	@Autowired
	BlogDAO dao; 
	
	//Routing to pages
	@RequestMapping(path="splash.do", method=RequestMethod.GET)
	public ModelAndView splash(HttpSession session) {
		User user =(User) session.getAttribute("user");
		ModelAndView mv = new ModelAndView("splash"); 
		if(user != null && user.isAdmin()) {
			session.setAttribute("admin", user.getUserName()); 
			return mv;
		}
		else if(user !=null) {
			session.setAttribute("userName", user.getUserName());
			session.setAttribute("admin", "Admin log-in");
			return mv; 
		}
		session.setAttribute("userName", "Log-in");
		session.setAttribute("admin", "Admin log-in");
		return mv; 
	}
	@RequestMapping(path="browse.do", method=RequestMethod.GET)
	public String browse() {
		return "browse"; 
	}
	@RequestMapping(path="admin.do", method=RequestMethod.GET)
	public ModelAndView admin(HttpSession session) {
		ModelAndView mv = new ModelAndView("adminLogIn"); 
		
		User user =(User) session.getAttribute("user");
		if(user != null && user.isAdmin()) {
			mv.setViewName("redirect: goToAdmin.do");
			return mv;
		}
		return mv ; 
	}
}
