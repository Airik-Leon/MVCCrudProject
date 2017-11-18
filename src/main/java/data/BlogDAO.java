package data;

import java.util.List;

public interface BlogDAO {

	public Post createPost(Post post); //create
	Post getPost(int id);
	public Post editPost(Post post); // update
	public Post deletePost(Post post); //delete
	//User
	public User createUser(User user); //create
	public User editUser(User user); //update
	public User deleteUser(User user); //delete
	List<User> getUsers();
	User getUserByUserName(String name);
	int getUserTotal();
	int getPostTotal();
	List<Post> getPosts();
	List<Post> getPostsByCategory(String category);
	User getUserById(int id);
	List<Post> getPostReplies(Post post);
	Post getReply(int id);
	Post createReply(SubPost post);
	Post editReply(Post post);
	Post deleteReply(Post post);

}
