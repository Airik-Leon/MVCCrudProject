package controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import data.PostDAO;
import data.User;
@Controller
public class AdminController {
	private final static  String NOT_EXIST = "User does not exist try again"; 
	@Autowired
	private PostDAO dao; 
	private List<String> activityLog = new ArrayList<>(); 
	//Page routing 
	@RequestMapping("log-in.do")
	public ModelAndView crud(@RequestParam("userName")String userName
			, @RequestParam("password")String pw, HttpSession session) {
		ModelAndView mv = new ModelAndView("adminLogIn");
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
		else if(user.getPassword().equals(pw)) { 
			int postCount = dao.getPostTotal(); 
			int userCount = dao.getUserTotal(); 	
			session.setAttribute("user", user);
			mv.addObject("postCount", postCount); 
			mv.addObject("userCount", userCount);
			mv.setViewName("admin");
			return mv; 
		}
		return mv; 
	}
	@RequestMapping("goToAdmin.do")
	public ModelAndView goToAdminPage() {
		ModelAndView mv = new ModelAndView("admin"); 
		int postCount = dao.getPostTotal(); 
		int userCount = dao.getUserTotal(); 		
		mv.addObject("postCount", postCount); 
		mv.addObject("userCount", userCount); 
		return mv; 
	}
	@RequestMapping("goToCreateUser.do")
	public String goToCreateUser(Model model) {
		User user = new User();
		model.addAttribute("user", user); 
		return "createUser"; 
	}
	@RequestMapping("goToUserInfo.do")
	public String goToUserInfo() {
		return "userInfo"; 
	}
	//Create User
	@RequestMapping(path="createUser.do" , method=RequestMethod.POST)
	public ModelAndView createUser(@Valid User user, Errors e) 
	{
		StringBuilder sb = new StringBuilder(); 
		ModelAndView mv = new ModelAndView("createUser"); 
		if(e.hasErrors()) {
			mv.addObject("error", "You must fill out all fields");
			return mv; 
		}
		else if(user.getFirstName().equals("")) {
			mv.addObject("error", "Name must be longer");
			return mv; 
		}
		//Check if userName taken
		else if(dao.getUserByUserName(user.getUserName()) != null) {
			mv.addObject("error", "User name is taken choose a different one");
			return mv; 
		}
//		user.setId(dao.getUsers().get(dao.getUsers().size()-1).getId() + 1);
		user.setAccountOrigin(LocalDate.now());
		dao.createUser(user);
		sb.append("Date: "); 
		sb.append(LocalDate.now()); 
		sb.append(user.toString()); 
		sb.append(" created"); 
		activityLog.add(sb.toString()); 
		int  userCount = dao.getUserTotal(); 
		int postCount = dao.getPostTotal();
		mv.addObject("activityLog", activityLog); 
		mv.addObject("userCount", userCount);
		mv.addObject("postCount", postCount);
		mv.setViewName("admin");
		return mv; 
	}
	@RequestMapping("searchById.do")
	public ModelAndView adminsearchById(@RequestParam("id")int id, HttpSession session) {
		ModelAndView mv = new ModelAndView("userInfo");
		User user = dao.getUser(id); 
		if(user == null) {
			String result = null; 
			mv.addObject("result", result);
			return mv; 
		}
		session.setAttribute("result", user);
		mv.addObject("result", user ); 
		return mv; 
	}
	@RequestMapping("searchByUserName.do")
	public ModelAndView adminSearchByUserName(@RequestParam("userName") String username, HttpSession session) {
		ModelAndView mv = new ModelAndView("userInfo"); 
		User user = dao.getUserByUserName(username); 
		if(user == null) {
			String result = "User does not exist"; 
			mv.addObject("result", null);
			return mv;
		}
		session.setAttribute("result", user);
		mv.addObject("result", user); 
		return mv; 
	}
	@RequestMapping("goToUpdateUser.do")
	public String goToUpdateUser(Model model) {
		User user = new User(); 
		model.addAttribute("user", user); 
		return "updateUser"; 
	}
	
	@RequestMapping("updateUser.do")
	public ModelAndView updateUser(User user) {
		StringBuilder sb = new StringBuilder(); 
		ModelAndView mv = new ModelAndView("admin"); 
		dao.editUser(user); 
		int userCount = dao.getUserTotal(); 
		int postCount = dao.getPostTotal(); 
		
		sb.append("Date: "); 
		sb.append(LocalDate.now()); 
		sb.append(user.toString()); 
		sb.append(" updated"); 
		activityLog.add(sb.toString()); 
		mv.addObject("activityLog", activityLog); 
		mv.addObject("userCount", userCount); 
		mv.addObject("postCount", postCount); 
		mv.addObject("user", user); 
		return mv; 
	}
	@RequestMapping("deleteUser.do")
	public ModelAndView deleteUser(@RequestParam("id") int id) {
		StringBuilder sb = new StringBuilder(); 
		ModelAndView mv = new ModelAndView();
		User user = dao.getUser(id); 
		mv.addObject("logDelete", dao.deleteUser(user));
		int postCount = dao.getPostTotal(); 
		int userCount = dao.getUserTotal(); 
		
		sb.append("Date: "); 
		sb.append(LocalDate.now()); 
		sb.append(user.toString()); 
		sb.append(" deleted"); 
		activityLog.add(sb.toString());
		mv.addObject("activityLog", activityLog); 
		mv.addObject("postCount", postCount); 
		mv.addObject("userCount", userCount); 
		mv.setViewName("admin");
		return mv; 
	}

}
