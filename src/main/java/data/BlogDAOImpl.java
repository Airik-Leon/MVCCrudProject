package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class BlogDAOImpl implements BlogDAO {
	private static String url = "jdbc:mysql://localhost:3306/blogdb";
	private String user = "airik";
	private String pass = "io";

	public BlogDAOImpl(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("Error loading MySQL");
		}
	}
	@Override
	public Post createPost(Post post) {	
		StringBuilder sql = new StringBuilder();
		int rowsAffected; 
		int returnedId; 
		LocalDateTime returnedTimeStamp; 
		
		sql.append("INSERT INTO post (user_id, title, message, category_id, post_stamp ");
		sql.append("VALUES(?, ?, ? , ?, (SELECT id FROM category WHERE name = ?) "); 

		Connection conn = null; 
		try {
			conn = DriverManager.getConnection(url, user, pass); 
			conn.setAutoCommit(false);
			PreparedStatement statement = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS); 			
			statement.setInt(1, post.getUserId());
			statement.setString(2, post.getTitle());
			statement.setString(3,  post.getMessage());
			statement.setString(4, "\'" + post.getCategory() + "\'");
			
			//Convert localdate to SQL timestamp
			Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
			statement.setTimestamp(5, timestamp);
			
			rowsAffected = statement.executeUpdate();
			System.out.println(rowsAffected);
			ResultSet key = statement.getGeneratedKeys();
			
			while(key.next()) {
				returnedId = key.getInt(1); 
				post.setPostID(returnedId);
				returnedTimeStamp =   key.getTimestamp(5).toLocalDateTime(); 
				post.setPostStamp(returnedTimeStamp);
			}			
			conn.commit();
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null; 
		}
		return post;
	}
	@Override
	public Post getPost(int id) {
		StringBuilder sql = new StringBuilder();	
		Post post = null; 
		
		sql.append("SELECT p.id, p.user_id, p.title, p.message, p.post_stamp, c.name"); 
		sql.append(" FROM post p JOIN category c  ON p.category_id = c.id WHERE p.id = ?");
		
		
		Connection conn = null; 
		try {
			conn = DriverManager.getConnection(url, user, pass); 
			conn.setAutoCommit(false);
			PreparedStatement statement = conn.prepareStatement(sql.toString()); 
			//Send in ID
			statement.setInt(1, id);
			//Get values 
			ResultSet rs = statement.executeQuery();
			int postId = rs.getInt(1);
			int userId = rs.getInt(2); 
			String title = rs.getString(3); 
			String message = rs.getString(4); 
			LocalDateTime postOrigin = rs.getTimestamp(5).toLocalDateTime();
			String category = rs.getString(6); 
			post = new Post(postId, userId, title, message, postOrigin, category); 
			conn.commit();
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null; 
		}
		return post;
	}
	@Override
	public List<Post> getPosts() {
		List<Post> allPosts = new ArrayList<>(); 
		StringBuilder sql = new StringBuilder(); 

		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			sql.append("SELECT p.id, p.user_id, p.title, p.message, p.post_stamp, c.name"); 
			sql.append(" FROM post p JOIN category c  ON p.category_id = c.id ORDER p.post_stamp");
			PreparedStatement stmt = conn.prepareStatement(sql.toString());
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt(1); 
				int userId = rs.getInt(2); 
				String title = rs.getString(3); 
				String message = rs.getString(4); 
				LocalDateTime postStamp = rs.getTimestamp(5).toLocalDateTime(); 
				String category = rs.getString(6); 
				
				Post post = new Post(id, userId, title, message, postStamp, category); 
				allPosts.add(post); 
			}
			
			rs.close();
			stmt.close();
			conn.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return allPosts;	
	}
	@Override
	public Post editPost(Post post) {
		StringBuilder sql = new StringBuilder(); 
		
		sql.append("UPDATE post SET"); 
		sql.append("title = ?"); 
		sql.append("message = ?"); 
		sql.append("category_id = (SELECT id FROM category WHERE name = ?) "); 
		sql.append("WHERE id = ? "); 
		
		Connection conn = null; 
		try {
			conn = DriverManager.getConnection(url, user, pass); 
			conn.setAutoCommit(false);
			PreparedStatement statement = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS); 			
			statement.setString(1, post.getTitle());
			statement.setString(2,  post.getMessage());
			statement.setString(3, "\'" + post.getCategory() + "\'");
			statement.setInt(4, post.getUserId());
			
			ResultSet key = statement.getGeneratedKeys();
			
			while(key.next()) {
				String returnedTitle = key.getString(1); 
				String returnedMessage = key.getString(2);
				
				String getCategory = "SELECT name FROM category WHERE id = ?"; 
				PreparedStatement categoryStatement = conn.prepareStatement(getCategory, Statement.RETURN_GENERATED_KEYS); 			
				categoryStatement.setInt(1, key.getInt(3));
				categoryStatement.executeQuery(); 
				ResultSet categoryKey = categoryStatement.getGeneratedKeys();
				
				String returnedCategory = post.getCategory(); 
				if(categoryKey.next()) {
					returnedCategory = categoryKey.getString(1); 
				}
				post.setTitle(returnedTitle); 
				post.setTitle(returnedMessage); 
				post.setCategory(returnedCategory);
			}			
			conn.commit();
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null; 
		}
		return post;
	}

	@Override
	public Post deletePost(Post post) {
		String sql = "DELETE FROM post WHERE id = ?";
		Connection conn = null; 
		try {
			conn = DriverManager.getConnection(url, user, pass); 
			conn.setAutoCommit(false);
			
			PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); 
			st.setInt(1, post.getPostID());
			conn.commit();
			st.close();
			conn.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null; 
		}
		return post;
	}

	@Override
	public User createUser(User newUser) {
		StringBuilder sql = new StringBuilder();
		int returnedId; 
		LocalDateTime returnedTimeStamp; 
		
		sql.append("INSERT INTO user (first_name, last_name, username, password, account_origin, role id ");
		sql.append("VALUES(?, ? , ?, ?, ?, (SELECT id FROM role WHERE name = ?) "); 

		Connection conn = null; 
		try {
			conn = DriverManager.getConnection(url, user, pass); 
			conn.setAutoCommit(false);
			PreparedStatement statement = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS); 			
			statement.setString(1, newUser.getFirstName());
			statement.setString(2, newUser.getLastName());
			statement.setString(3, newUser.getUserName());
			statement.setString(4, newUser.getPassword());
			statement.setString(5, "\'" + newUser.getRole() + "\'");
			
			//Convert localdate to SQL timestamp
			Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
			statement.setTimestamp(5, timestamp);
			
			ResultSet key = statement.getGeneratedKeys();
			
			while(key.next()) {
				returnedId = key.getInt(1); 
				newUser.setId(returnedId);
				returnedTimeStamp =   key.getTimestamp(5).toLocalDateTime(); 
				newUser.setAccountOrigin(returnedTimeStamp);
			}			
			conn.commit();
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null; 
		}
		return newUser;
	}

	@Override
	public User getUserById(int id) {
		StringBuilder sql = new StringBuilder();	
		User foundUser = null; 
		
		sql.append("SELECT u.id, u.first_name, u.last_name, u.username, u.password, u.account_origin, r.name"); 
		sql.append(" FROM user u JOIN role r  ON u.role_id = r.id WHERE u.id = ?");
		
		
		Connection conn = null; 
		try {
			conn = DriverManager.getConnection(url, user, pass); 
			conn.setAutoCommit(false);
			PreparedStatement statement = conn.prepareStatement(sql.toString()); 
			//Send in ID
			statement.setInt(1, id);
			//Get values 
			ResultSet rs = statement.executeQuery();
			int userId = rs.getInt(1);
			String firstName = rs.getString(2); 
			String lastName = rs.getString(3); 
			String username = rs.getString(4); 
			String password = rs.getString(4); 
			LocalDateTime accountOrigin = rs.getTimestamp(5).toLocalDateTime();
			String role = rs.getString(6); 
			foundUser = new User(userId, firstName, lastName, username, password,
					accountOrigin, role); 
			conn.commit();
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null; 
		}
		return foundUser;
	}
	@Override
	public User getUserByUserName(String userName) {
		User foundUser = null;
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT * FROM user WHERE username = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "\'"+ userName + "\'");
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				int id = rs.getInt(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String username = rs.getString(4);
				String password = rs.getString(5);
				LocalDateTime accountOrigin = rs.getTimestamp(6).toLocalDateTime();
				String role = rs.getString(7);

				foundUser = new User(id, firstName, lastName, username, password
						, accountOrigin, role);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null; 
		}
		return foundUser;
	}

	@Override
	public User editUser(User editUser) {
		StringBuilder sql = new StringBuilder(); 
		
		sql.append("UPDATE user SET"); 
		sql.append("first_name = ?"); 
		sql.append("last_name = ?"); 
		sql.append("username = ? "); 
		sql.append("password = ? "); 
		sql.append("WHERE id = ? "); 
		
		Connection conn = null; 
		try {
			conn = DriverManager.getConnection(url, user, pass); 
			conn.setAutoCommit(false);
			PreparedStatement statement = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS); 			
			statement.setString(1, editUser.getFirstName());
			statement.setString(2, editUser.getLastName());
			statement.setString(3, editUser.getUserName());
			statement.setString(4, editUser.getPassword());			
			statement.setInt(5, editUser.getId());
			
			ResultSet key = statement.getGeneratedKeys();
			
			if(key.next()) {
				String returnedFirstName = key.getString(1); 
				String returnedLastName = key.getString(2);
				String returnedUserName = key.getString(3);
				String returnedPassword = key.getString(4);
								
				editUser.setFirstName(returnedFirstName); 
				editUser.setLastName(returnedLastName); 
				editUser.setUserName(returnedUserName); 
				editUser.setPassword(returnedPassword); 
			}			
			conn.commit();
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null; 
		}
		return editUser;
	}

	@Override
	public User deleteUser(User deletedUser) {
		String sql = "DELETE FROM user WHERE id = ?";
		Connection conn = null; 
		try {
			conn = DriverManager.getConnection(url, user, pass); 
			conn.setAutoCommit(false);
			
			PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); 
			st.setInt(1, deletedUser.getId());
			conn.commit();
			st.close();
			conn.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null; 
		}
		return deletedUser;	
	}
	@Override
	public List<User> getUsers(){
		List<User> allUsers = new ArrayList<>(); 
		StringBuilder sql = new StringBuilder(); 

		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			sql.append("SELECT u.id, u.first_name, u.last_name, u.user_name, u.password, u.account_origin, r.name"); 
			sql.append(" FROM user u JOIN role r ON u.role_id = r.id ORDER BY u.account_origin");
			PreparedStatement stmt = conn.prepareStatement(sql.toString());
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt(1); 
				String firstName = rs.getString(2); 
				String lastName = rs.getString(3); 
				String userName = rs.getString(4); 
				String password = rs.getString(5); 
				LocalDateTime accountOrigin = rs.getTimestamp(5).toLocalDateTime(); 
				String role = rs.getString(6); 
				
				User user = new User(id, firstName, lastName, userName, password, accountOrigin,
						role); 
				allUsers.add(user); 
			}
			
			rs.close();
			stmt.close();
			conn.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return null; 
		}
		return allUsers;		
	}
	public int getUserTotal() {
		String sql = "SELECT COUNT(id) FROM  user";
		int totalUsers = 0;
		Connection conn = null; 
		try {
			conn = DriverManager.getConnection(url, user, pass); 
			conn.setAutoCommit(false);
			PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); 
			ResultSet result = st.executeQuery();
			totalUsers = result.getInt(1);
			conn.commit();
			st.close();
			conn.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
			return 0; 
		}
		return totalUsers;	
	}
	public int getPostTotal() {
		String sql = "SELECT COUNT(id) FROM  post";
		int totalPosts = 0;
		Connection conn = null; 
		try {
			conn = DriverManager.getConnection(url, user, pass); 
			conn.setAutoCommit(false);
			PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); 
			ResultSet result = st.executeQuery();
			totalPosts = result.getInt(1);
			conn.commit();
			st.close();
			conn.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
			return 0; 
		}
		return totalPosts;	
	}
	@Override
	public List<Post> getPostsByCategory(String category){
		List<Post> list = new ArrayList<>(); 
		StringBuilder sql = new StringBuilder(); 

		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			
			 sql.append("SELECT p.id, p.user_id, p.title, p.message, p.post_stamp, c.name ");
			 sql.append(" FROM post p JOIN category c ON  p.category_id = c.id "); 
			 sql.append(" WHERE c.name LIKE ? " ); 
			 
			PreparedStatement stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, "%" + category + "%");

			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				int id  = rs.getInt(1);
				int userId = rs.getInt(2); 
				String title = rs.getString(3); 
				String message = rs.getString(4); 
				LocalDateTime postStamp = rs.getTimestamp(5).toLocalDateTime();
				String categoryName = rs.getString(6);
				Post post = new Post(id, userId, title, message, postStamp, categoryName); 
				list.add(post); 
			}
			
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null; 
		}
		return list;
	}
	@Override
	public Post createReply(SubPost post) {	
		StringBuilder sql = new StringBuilder();
		int rowsAffected; 
		int returnedId; 
		LocalDateTime returnedTimeStamp; 
		
		sql.append("INSERT INTO post_reply (parent_id, user_id, title, message, category_id, post_stamp ");
		sql.append("VALUES(?, ?, ? , ?, (SELECT id FROM category WHERE name = ?) "); 

		Connection conn = null; 
		try {
			conn = DriverManager.getConnection(url, user, pass); 
			conn.setAutoCommit(false);
			PreparedStatement statement = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS); 			
			statement.setInt(1, post.getParentId());
			statement.setInt(2, post.getUserId());
			statement.setString(3, post.getTitle());
			statement.setString(4,  post.getMessage());
			statement.setString(5, "\'" + post.getCategory() + "\'");
			
			//Convert localdate to SQL timestamp
			Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
			statement.setTimestamp(5, timestamp);
			
			rowsAffected = statement.executeUpdate();
			System.out.println(rowsAffected);
			ResultSet key = statement.getGeneratedKeys();
			
			while(key.next()) {
				returnedId = key.getInt(1); 
				post.setPostID(returnedId);
				returnedTimeStamp =   key.getTimestamp(5).toLocalDateTime(); 
				post.setPostStamp(returnedTimeStamp);
			}			
			conn.commit();
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null; 
		}
		return post;
	}
	@Override
	public Post getReply(int id) {
		StringBuilder sql = new StringBuilder();	
		SubPost post = null; 
		
		sql.append("SELECT p.id, p.parent.id, p.user_id, p.title, p.message, p.post_stamp, c.name"); 
		sql.append(" FROM post_reply p JOIN category c  ON p.category_id = c.id WHERE p.id = ?");
		
		
		Connection conn = null; 
		try {
			conn = DriverManager.getConnection(url, user, pass); 
			conn.setAutoCommit(false);
			PreparedStatement statement = conn.prepareStatement(sql.toString()); 
			//Send in ID
			statement.setInt(1, id);
			//Get values 
			ResultSet rs = statement.executeQuery();
			int postId = rs.getInt(1);
			int parentId = rs.getInt(2); 
			int userId = rs.getInt(3); 
			String title = rs.getString(4); 
			String message = rs.getString(5); 
			LocalDateTime postOrigin = rs.getTimestamp(6).toLocalDateTime();
			String category = rs.getString(7); 
			post = new SubPost(postId, parentId, userId, title, message, postOrigin, category); 
			conn.commit();
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null; 
		}
		return post;
	}
	@Override
	public List<Post> getPostReplies(Post post) {
		List<Post> replyPosts = new ArrayList<>(); 
		StringBuilder sql = new StringBuilder(); 

		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			sql.append("SELECT p.id, p.user_id, p.title, p.message, p.post_stamp, c.name"); 
			sql.append("FROM post p JOIN category c  ON p.category_id = c.id ");
			sql.append("JOIN post_reply r ON p.id = r.parent_post");
			sql.append("ORDER BY p.post_stamp");
			PreparedStatement stmt = conn.prepareStatement(sql.toString());
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt(1); 
				int parentId = rs.getInt(2); 
				int userId = rs.getInt(3); 
				String title = rs.getString(4); 
				String message = rs.getString(5); 
				LocalDateTime postStamp = rs.getTimestamp(6).toLocalDateTime(); 
				String category = rs.getString(7); 
				
				Post subPost = new SubPost(id, parentId,  userId, title, message, postStamp, category); 
				replyPosts.add(subPost); 
			}
			rs.close();
			stmt.close();
			conn.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return null; 
		}
		return replyPosts;	
	}
	@Override
	public Post editReply(Post post) {
		StringBuilder sql = new StringBuilder(); 
		
		sql.append("UPDATE post SET"); 
		sql.append("title = ?"); 
		sql.append("message = ?"); 
		sql.append("WHERE id = ? "); 
		
		Connection conn = null; 
		try {
			conn = DriverManager.getConnection(url, user, pass); 
			conn.setAutoCommit(false);
			PreparedStatement statement = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS); 			
			statement.setString(1, post.getTitle());
			statement.setString(2,  post.getMessage());
			statement.setInt(3, post.getUserId());
			
			ResultSet key = statement.getGeneratedKeys();
			
			while(key.next()) {
				String returnedTitle = key.getString(1); 
				String returnedMessage = key.getString(2);
				post.setTitle(returnedTitle); 
				post.setTitle(returnedMessage); 
			}			
			conn.commit();
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null; 
		}
		return post;
	}

	@Override
	public Post deleteReply(Post post) {
		String sql = "DELETE FROM post WHERE id = ?";
		Connection conn = null; 
		try {
			conn = DriverManager.getConnection(url, user, pass); 
			conn.setAutoCommit(false);
			
			PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); 
			st.setInt(1, post.getPostID());
			conn.commit();
			st.close();
			conn.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null; 
		}
		return post;
	}

}
