package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
@Component
public class BlogDAOImpl implements PostDAO {
	private final String USERS = "/WEB-INF/resources/users.txt"; 
	private final String POSTS = "/WEB-INFresources/posts.txt"; 
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
				if(tokens[6].trim().equals("admin"))user.setAdmin(true);
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
				String category = tokens[5].trim(); 
				Post post = new Post(postId, title, message, accountOrigin, userId); 
				post.setCategory(category);
				post.setUserName(getUser(userId).getUserName());
				postCount++; 
				userMap.get(post.getUserId()).getPosts().put(post.getPostID(), post); 
				postMap.put(post.getPostID(), post); 
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	@Override
	public void saveUsers() {
		File users = new File(wac.getServletContext().getRealPath("/WEB-INF/resources/users.txt"));        
        FileOutputStream fos; 
        List<User> list = getUsers(); 
        
		StringBuilder sb = new StringBuilder(); 
		String amp = " & "; 
		try {
			fos = new FileOutputStream(users);
			for (User user : list) {
				sb.append(user.getId()); 
				sb.append(amp); 
				sb.append(user.getFirstName());
				sb.append(amp);
				sb.append(user.getLastName());
				sb.append(amp);
				sb.append(user.getPassword());
				sb.append(amp);
				sb.append(user.getAccountOrigin());
				sb.append(amp);
				if(user.isAdmin()) {
					sb.append("admin");
				}
				else {
					sb.append("user"); 
				}
				fos.write(sb.toString().getBytes());
			}
			fos.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void savePosts() {
		File users = new File(wac.getServletContext().getRealPath("/WEB-INF/resources/posts.txt"));        
        FileOutputStream fos; 
        List<Post> list = getPosts(); 
        
		StringBuilder sb = new StringBuilder(); 
		String amp = " & "; 
		try {
			fos = new FileOutputStream(users);
			for (Post post : list) {
				sb.append(post.getPostID()); 
				sb.append(amp); 
				sb.append(post.getTitle());
				sb.append(amp);
				sb.append(post.getMessage());
				sb.append(amp);
				sb.append(post.getPostStamp());
				sb.append(amp);
				sb.append(post.getUserId());
				sb.append(amp);
				sb.append(post.getUserName());
				sb.append(amp);
				sb.append(post.getCategory());

				fos.write(sb.toString().getBytes());
			}
			fos.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
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
		return currentUser.getPosts().get(id); 
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
	@Override
	public List<Post> getPostsByCategory(String category){
		BiPredicate<Post, String> filterByCategory =
				( p, s)-> p.getCategory().equals(s); 
		return this.filter(filterByCategory, category); 
	}
	private List<Post> filter(BiPredicate<Post, String> matcher, String category){
		List<Post> filtered = new ArrayList<>(); 
		List<Post> list = getPosts(); 
		for (Post post : list) {
			if(matcher.test(post, category)) {
				filtered.add(post); 
			}
		}
		return filtered; 
	}
}
