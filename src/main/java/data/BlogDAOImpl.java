package data;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
@Component
public class BlogDAOImpl implements PostDAO {
	
	private Map<Integer, User> userMap; 
	private Map<Integer, Post>	postMap;
	private static int userCount = 0; 
	private static int postCount = 0; 
	private int id = 0; 
	
	@Autowired
	private WebApplicationContext wac;

	
	public BlogDAOImpl(){
		userMap = new HashMap<>(); 
		postMap = new HashMap<>(); 
		initUsers(); 
		initPosts(); 
	}
	@PostConstruct
	private void initUsers() {
		// Retrieve an input stream from the servlet context
		// rather than directly from the file system
		try{
			InputStream is = wac.getServletContext().getResourceAsStream("/WEB-INF/resources/users.txt");
			BufferedReader buf = new BufferedReader(new InputStreamReader(is));
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-dd-MM"); 

			String line; 
			while ((line = buf.readLine()) != null) {
				String[] tokens = line.split("&");
				int id = Integer.parseInt(tokens[0].trim()); 
				String firstName = tokens[1].trim();
				String lastName = tokens[2].trim();
				String userName = tokens[3].trim();
				String password = tokens[4].trim();
				LocalDate accountOrigin = LocalDate.parse(tokens[5].trim(), formatter); 
				User user = new User(id, firstName, lastName, userName, password, accountOrigin); 
				userCount++; 
				userMap.put(user.getId(), user); 
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	@PostConstruct
	private void initPosts() {
		// Retrieve an input stream from the servlet context
		// rather than directly from the file system
		try{ 
			InputStream is = wac.getServletContext().getResourceAsStream("/WEB-INF/resources/posts.txt");
			BufferedReader buf = new BufferedReader(new InputStreamReader(is));
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-dd-MM"); 

			String line; 
			while ((line = buf.readLine()) != null) {
				String[] tokens = line.split("&");
				int postId = Integer.parseInt(tokens[0].trim()); 
				String title = tokens[1].trim();
				String message = tokens[2].trim();
				LocalDate accountOrigin = LocalDate.parse(tokens[3].trim(), formatter); 
				int userId  = Integer.parseInt(tokens[4].trim());
				Post post = new Post(postId, title, message, accountOrigin, userId); 
				postCount++; 
				userMap.get(post.getUserId()).getPosts().put(post.getPostID(), post); 
				postMap.put(post.getPostID(), post); 
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	private void loadState() {
		
	}
	@Override
	public Post createPost(Post post) {
		User user = userMap.get(post.getUserId()); 
		postMap.put(post.getPostID(), post);
		postCount++;
		return user.getPosts().put(post.getPostID(), post); 
	}
	@Override
	public Post getPost(User user, int id) {
		User currentUser = userMap.get(user.getId()); 
		currentUser.getPosts().get(id); 
		return null;
	}
	@Override
	public List<Post> getPosts() {
		return new ArrayList<Post>(postMap.values()); 
	}
	@Override
	public Post editPost(Post post) {
		User user = userMap.get(post.getUserId()); 
		return user.getPosts().put(post.getPostID(), post); 
	}

	@Override
	public Post deletePost(Post post) {
		User user = userMap.get(post.getUserId()); 
		return user.getPosts().remove(post.getPostID()); 
	}

	@Override
	public User createUser(User user) {
		id = getUsers().get(getUsers().size()-1).getId(); 
		id++; 
		user.setId(id);
		userCount++; 
		userMap.put(user.getId(), user);
		return user; 
	}

	@Override
	public User getUser(int id) {
		User user = userMap.get(id); 
		return user;
	}
	@Override
	public User getUserByUserName(String name) {
		List<User> list = getUsers(); 
		User temp =null; 
		for (User user : list) {
			if(name.trim().equals(user.getUserName())){
				temp = user; 
			}
		}
		return temp; 
	}

	@Override
	public User editUser(User user) {
		return userMap.put(user.getId(), user); 
	}

	@Override
	public User deleteUser(User user) {
		return userMap.remove(user.getId()); 
	}
	@Override
	public List<User> getUsers(){
		return new ArrayList<User>(userMap.values()); 
	}
	public int getUserTotal() {
		return getUsers().size(); 
	}
	public int getPostTotal() {
		return BlogDAOImpl.postCount; 
	}
}
