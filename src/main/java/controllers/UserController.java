package controllers;

import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import data.BlogDAO;
import data.Post;
import data.User;

@Controller
public class UserController {
	private final static String NOT_EXIST = "User does not exist try again";
	@Autowired
	BlogDAO dao;

	@RequestMapping("userLogIn.do")
	public ModelAndView userLogIn(HttpSession session) {
		ModelAndView mv = new ModelAndView("userLogIn");
		User user = (User) session.getAttribute("user");
		if (user != null && !user.isAdmin()) {
			session.setAttribute("userName", user.getUserName());
			session.setAttribute("admin", "Admin log-in");
			mv.setViewName("splash");
			return mv;
		}
		return mv;
	}

	@RequestMapping("user-LogIn.do")
	public ModelAndView crud(@RequestParam("userName") String userName, @RequestParam("password") String pw,
			HttpSession session) {
		ModelAndView mv = new ModelAndView("userLogIn");
		User user;
		// Empty UserName
		if (userName.equals("")) {
			mv.addObject("NOT_EXIST", NOT_EXIST);
			return mv;
		} else {
			user = dao.getUserByUserName(userName);
			System.out.println(user);
		}
		// User does not exist
		if (user == null) {
			mv.addObject("NOT_EXIST", NOT_EXIST);
			return mv;
		} // User exist check password
		else if (user.getPassword().equals(pw) && !user.isAdmin()) {
			
			session.setAttribute("user", user);
			session.setAttribute("userName", user.getUserName());
			session.setAttribute("admin", "Admin log-in");
			mv.setViewName("splash");
			return mv;
		} else if (user.getPassword().equals(pw) && user.isAdmin()) {
			mv.setViewName("adminLogIn");
			return mv;
		}
		return mv;
	}

	@RequestMapping("goToUserAddAccount.do")
	public String goToUserAddAccount(Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user != null && ! user.isAdmin()) {
			return "redirect: splash.do";
		}
		user = new User();
		model.addAttribute("user", user);
		return "userAddAccount";
	}

	@RequestMapping("addUserAccount.do")
	public ModelAndView addUserAccount(User user, HttpSession session) {
		ModelAndView mv = new ModelAndView("userAddAccount");

		if (user.getFirstName().equals("")) {
			mv.addObject("error", "Name must be longer");
			return mv;
		}
		// Check if userName taken
		else if (dao.getUserByUserName(user.getUserName()) != null) {
			mv.addObject("error", "User name is taken choose a different one");
			return mv;
		}
		dao.createUser(user);
		session.setAttribute("user", user);
		mv.setViewName("browse");
		return mv;
	}

	@RequestMapping("goToUserCreatePost.do")
	public String goTocreatePost(Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "splash";
		}
		Post post = new Post();
		model.addAttribute("post", post);
		return "userCreatePost";
	}

	@RequestMapping("userCreatePost.do")
	public ModelAndView userCreatePost(Post post, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		if (post == null) {
			mv.setViewName("redirect: splash.do");
			return mv;
		}
		mv.setViewName(post.getCategory().toString());
		post.setPostStamp(LocalDateTime.now().plusMinutes(0));
		User user = (User) session.getAttribute("user");
		int postId = dao.getPostTotal() + 1;
		post.setPostID(postId);
		post.setUserId(user.getId());
		dao.createPost(post);
		mv.addObject("posts", dao.getPostsByCategory(post.getCategory()));
		return mv;
	}

	@RequestMapping("userLogOut")
	public ModelAndView logOut(HttpSession session) {
		ModelAndView mv = new ModelAndView("redirect: splash.do");
		session.setAttribute("user", null);
		session.setAttribute("userName", "Log-in");
		session.setAttribute("admin", "Admin log-in");
		return mv;
	}

}
