package controllers;

import java.time.LocalDate;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
		ModelAndView mv = new ModelAndView(post.getCategory().toString());
		post.setPostStamp(LocalDate.now());
		User user = (User) session.getAttribute("user"); 
		int postId = dao.getPostTotal()+1; 
		post.setPostID(postId);
		post.setUserName(user.getUserName());
		post.setUserId(user.getId());
		System.out.println(post);
		dao.createPost(post); 
		mv.addObject("posts", dao.getPostsByCategory(post.getCategory())); 
		return mv; 
	}
}
