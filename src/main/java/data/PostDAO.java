package data;

import java.util.List;

public interface PostDAO {
	//post
	public Post createPost(Post post); //create
	Post getPost(User user, int id);
	public Post editPost(Post post); // update
	public Post deletePost(Post post); //delete
	//User
	public User createUser(User user); //create
	public User getUser(int id); // retrieve
	public User editUser(User user); //update
	public User deleteUser(User user); //delete
	List<User> getUsers();
	User getUserByUserName(String name);
	int getUserTotal();
	int getPostTotal();
	List<Post> getPosts();
	List<Post> getPostsByCategory(String category);
}
