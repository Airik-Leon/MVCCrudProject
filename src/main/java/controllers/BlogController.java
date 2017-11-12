package controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import data.PostDAO;
import data.User;

@Controller
public class BlogController {
	private final static  String NOT_EXIST = "User does not exist try again"; 

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
		else if(user !=null) {
			mv.addObject("userName", user.getUserName()); 
			mv.addObject("admin", "Admin log-in");
			return mv; 
		}
		mv.addObject("userName", "Log-in");
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
			return "admin";
		}
		return "adminLogIn"; 
	}
	@RequestMapping("userLogIn.do")
	public ModelAndView userLogIn(HttpSession session) {
		ModelAndView mv = new ModelAndView("userLogIn");
		User user = (User) session.getAttribute("user"); 
		if(user != null && !user.isAdmin()) {
			session.setAttribute("userName", user.getUserName());
			session.setAttribute("admin", "Admin log-in");
			mv.setViewName("splash");
			return mv; 
		}
		return mv; 
	}
	
	@RequestMapping("user-LogIn.do")
	public ModelAndView crud(@RequestParam("userName")String userName
			, @RequestParam("password")String pw, HttpSession session) {
		ModelAndView mv = new ModelAndView("userLogIn");
		User user; 
		//Empty UserName
		if(userName.equals("")) {
			mv.addObject("NOT_EXIST", NOT_EXIST);
			return mv; 
		}
		else {
		 user = dao.getUserByUserName(userName); 
		}
		//User does not exist
		if(user == null) {
			mv.addObject("NOT_EXIST", NOT_EXIST);
			return mv; 
		}//User exist check password
		else if(user.getPassword().equals(pw) && !user.isAdmin()) { 	
			session.setAttribute("user", user);
			mv.addObject("userName", user.getUserName());
			mv.addObject("admin", "Admin log-in");
			mv.setViewName("splash");
			return mv; 
		}
		else if(user.getPassword().equals(pw) && user.isAdmin()) {
			mv.setViewName("adminLogIn");
			return mv; 
		}
		return mv; 
	}
	@RequestMapping("goToUserAddAccount.do")
	public String goToUserAddAccount(Model model) {		
		User user = new User(); 
		model.addAttribute("user", user); 
		return "userAddAccount"; 
	}
	@RequestMapping("addUserAccount.do")
	public ModelAndView addUserAccount(User user) {
		ModelAndView mv = new ModelAndView("browse");
		return mv; 
	}
}
