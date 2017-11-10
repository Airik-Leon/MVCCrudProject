package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import data.PostDAO;

@Controller
public class BlogController {
	@Autowired
	PostDAO dao; 
	
	//Routing to pages
	@RequestMapping(path="splash.do", method=RequestMethod.GET)
	public String splash() {
		return "splash"; 
	}
	@RequestMapping(path="browse.do", method=RequestMethod.GET)
	public String browse() {
		return "browse"; 
	}
	@RequestMapping(path="admin.do", method=RequestMethod.GET)
	public String admin() {
		return "admin"; 
	}
}
