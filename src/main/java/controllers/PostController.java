package controllers;

import java.time.LocalDate;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import data.Post;
import data.PostDAO;
import data.User;

@Controller
public class PostController {
	@Autowired
	PostDAO dao; 
	
	@RequestMapping("goToCreatePost.do")
	public String goToCreatePost(Model model) {
		Post post = new Post(); 
		model.addAttribute("post", post);
		return "createPost"; 
	}
	@RequestMapping("createPost.do")
	public ModelAndView createPost(Post post, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		if(post == null) {
			mv.setViewName("redirect: splash.do");
			return mv; 
		}
		mv.setViewName(post.getCategory().toString());
		post.setPostStamp(LocalDate.now());
		User user = (User) session.getAttribute("user"); 
		int postId = dao.getPostTotal()+1; 
		post.setPostID(postId);
		post.setUserName(user.getUserName());
		post.setUserId(user.getId());
		System.out.println(post);
		dao.createPost(post); 
		dao.savePosts();
		mv.addObject("posts", dao.getPostsByCategory(post.getCategory())); 
		return mv; 
	}
	@RequestMapping("goToReply.do")
	public ModelAndView createPost(HttpSession session
			, @RequestParam("postId") int id
			, @RequestParam("reply") String reply
			, @RequestParam("postUserName") String userName) {
		ModelAndView mv = new ModelAndView();
		User user = (User) session.getAttribute("user");
		if(user == null) {
			mv.setViewName("redirect: userLogIn.do");
			return mv; 
		}
		User poster = dao.getUserByUserName(userName); 
		Post post = dao.getPost(poster, id); 
		Post userReply = new Post(); 
		
		userReply.setCategory(post.getCategory());
		userReply.setMessage(reply);
		userReply.setPostID(dao.getPostTotal()+1);
		userReply.setUserId(user.getId());
		userReply.setUserName(user.getUserName());
		userReply.setTitle("reply to: "+post.getUserName() + ": " + post.getTitle());
		userReply.setPostStamp(LocalDate.now());
		
		post.getReplies().add(userReply);
		user.getPosts().put(userReply.getPostID(), post); 
		mv.addObject("posts", dao.getPostsByCategory(post.getCategory())); 
		mv.setViewName("redirect: goTo" + post.getCategory().toString() + ".do");
		return mv; 
	}
}
