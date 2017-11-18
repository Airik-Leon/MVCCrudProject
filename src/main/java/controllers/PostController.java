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
import data.SubPost;
import data.User;

@Controller
public class PostController {
	@Autowired
	BlogDAO dao; 
	
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
		post.setPostStamp(LocalDateTime.now());
		User user = (User) session.getAttribute("user"); 
		int postId = dao.getPostTotal()+1; 
		post.setPostID(postId);
		post.setUserId(user.getId());
		System.out.println(post);
		dao.createPost(post); 
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
		Post post = dao.getPost(id); 
	
		SubPost userReply = new SubPost(); 
		userReply.setParentId(id);
		userReply.setUserId(user.getId());
		userReply.setTitle("reply to: "+ dao.getUserById(post.getUserId()).getUserName() + ": " + post.getTitle());
		userReply.setMessage(reply);
		userReply.setCategory(post.getCategory());
		
		dao.createReply(userReply); 
		
		mv.addObject("posts", dao.getPostsByCategory(post.getCategory())); 
		mv.setViewName("redirect: goTo" + post.getCategory().toString() + ".do");
		return mv; 
	}
}
