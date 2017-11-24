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
	private String user = "root";
	private String pass = "FGyu67%#S";

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

		sql.append("INSERT INTO post (user_id, title, message, post_stamp, category_id) ");
		sql.append(" VALUES(?, ?, ? , ?, (SELECT id FROM category WHERE name = ?) )");

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false);
			PreparedStatement statement = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, post.getUserId());
			statement.setString(2, post.getTitle());
			statement.setString(3,  post.getMessage());
			//Convert localdate to SQL timestamp
			Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
			statement.setTimestamp(4, timestamp);
			post.setPostStamp(timestamp.toLocalDateTime());
			statement.setString(5, post.getCategory());

			rowsAffected = statement.executeUpdate();
			System.out.println(rowsAffected);
			ResultSet key = statement.getGeneratedKeys();

			while(key.next()) {
				returnedId = key.getInt(1);
				post.setPostID(returnedId);
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
			if(rs.next()) {
				int postId = rs.getInt(1);
				int userId = rs.getInt(2);
				String title = rs.getString(3);
				String message = rs.getString(4);
				LocalDateTime postOrigin = rs.getTimestamp(5).toLocalDateTime();
				String category = rs.getString(6);
				post = new Post(postId, userId, title, message, postOrigin, category);
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
	public List<Post> getPosts() {
		List<Post> allPosts = new ArrayList<>();
		StringBuilder sql = new StringBuilder();

		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			sql.append("SELECT p.id, p.user_id, p.title, p.message, p.post_stamp, c.name, u.username");
			sql.append(" FROM post p JOIN category c  ON p.category_id = c.id ");
			sql.append(" JOIN user u ON p.user_id = u.id ORDER BY p.post_stamp ");
			PreparedStatement stmt = conn.prepareStatement(sql.toString());
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt(1);
				int userId = rs.getInt(2);
				String title = rs.getString(3);
				String message = rs.getString(4);
				LocalDateTime postStamp = rs.getTimestamp(5).toLocalDateTime();
				String category = rs.getString(6);
				String username = rs.getString(7);

				Post post = new Post(id, userId, title, message, postStamp, category);
				post.setUsername(username);
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
		sql.append(
				"INSERT INTO user (first_name, last_name, username, password, account_origin, role_id)"
				);
		sql.append(
				" VALUES(?, ? , ?, ?, ?, ?)"
				);

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false);
			PreparedStatement statement = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, newUser.getFirstName());
			statement.setString(2, newUser.getLastName());
			statement.setString(3, newUser.getUserName());
			statement.setString(4, newUser.getPassword());
			//Convert localdate to SQL timestamp
			Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
			newUser.setAccountOrigin(timestamp.toLocalDateTime());
			statement.setTimestamp(5, timestamp);

			statement.setInt(6, newUser.getRole());
			int uc = statement.executeUpdate();
			System.out.println(uc + " user created");
			ResultSet key = statement.getGeneratedKeys();

			while(key.next()) {
				returnedId = key.getInt(1);
				newUser.setId(returnedId);
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
	public User getUserById(int userId) {
		User foundUser = null;
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false);

			String sql = " SELECT id, first_name, last_name, username, password, account_origin, role_id FROM user WHERE id = ? " ;
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, userId);

			ResultSet rs = statement.executeQuery();

			if(rs.next()) {
				int id = rs.getInt(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String username = rs.getString(4);
				String password = rs.getString(5);
				LocalDateTime accountOrigin = rs.getTimestamp(6).toLocalDateTime();
				int role = rs.getInt(7);
				foundUser = new User(id, firstName, lastName, username, password,
						accountOrigin, role);
			}
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
			stmt.setString(1, userName);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				int id = rs.getInt(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String username = rs.getString(4);
				String password = rs.getString(5);
				LocalDateTime accountOrigin = rs.getTimestamp(6).toLocalDateTime();
				int role = rs.getInt(7);

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
		sql.append("Update user SET \n first_name = ?, \n last_name = ? , username = ?,  password = ?\n WHERE id = ? ");
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
			int uc = statement.executeUpdate();
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

			int uc = st.executeUpdate();
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
			sql.append("SELECT u.id, u.first_name, u.last_name, u.user_name, u.password, u.account_origin, u.id");
			sql.append(" FROM user u ORDER BY u.account_origin");
			PreparedStatement stmt = conn.prepareStatement(sql.toString());
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String userName = rs.getString(4);
				String password = rs.getString(5);
				LocalDateTime accountOrigin = rs.getTimestamp(5).toLocalDateTime();
				int role = rs.getInt(6);

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
			if(result.next()) {
				totalUsers = result.getInt(1);
			}
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
			if(result.next()) {
				totalPosts = result.getInt(1);
			}
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

			 sql.append("SELECT p.id, p.user_id, p.title, p.message, p.post_stamp, c.name, u.username ");
			 sql.append(" FROM post p JOIN category c ON  p.category_id = c.id ");
			 sql.append(" JOIN user u ON p.user_id = u.id ");
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
				String username = rs.getString(7);
				Post post = new Post(id, userId, title, message, postStamp, categoryName);
				post.setUsername(username);
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
	public SubPost createReply(SubPost post) {
		StringBuilder sql = new StringBuilder();
		int rowsAffected;
		int returnedId;

		sql.append("INSERT INTO post_reply (parent_post, user_id, title, message, post_stamp, category_id) ");
		sql.append(" VALUES(?, ?, ? , ?, ?, (SELECT id FROM category WHERE name = ?)) ");

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false);
			PreparedStatement statement = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			statement.setInt(1, post.getParentId());
			statement.setInt(2, post.getUserId());
			statement.setString(3, post.getTitle());
			statement.setString(4,  post.getMessage());
			//Convert localdate to SQL timestamp
			Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
			statement.setTimestamp(5, timestamp);
			statement.setString(6, post.getCategory() );


			rowsAffected = statement.executeUpdate();
			System.out.println(rowsAffected);
			ResultSet key = statement.getGeneratedKeys();

			while(key.next()) {
				returnedId = key.getInt(1);
				post.setPostID(returnedId);
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
	public List<SubPost> getPostReplies(Post post) {
		List<SubPost> replyPosts = new ArrayList<>();
		StringBuilder sql = new StringBuilder();

		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			sql.append("SELECT Distinct pr.id, pr.parent_post, u.username, pr.title, pr.message, pr.post_stamp, c.name ");
			sql.append(" FROM post_reply pr JOIN category c  ON pr.category_id = c.id  ");
			sql.append(" JOIN user u ON pr.user_id = u.id ");
			sql.append(" JOIN post p ON p.id = pr.parent_post WHERE pr.parent_post = ?");
			sql.append(" ORDER BY p.post_stamp ");

			PreparedStatement stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, post.getPostID());
//			stmt.setInt(2,  post.getPostID());
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt(1);
				int parentId = rs.getInt(2);
				String username = rs.getString(3);
				String title = rs.getString(4);
				String message = rs.getString(5);
				LocalDateTime postStamp = rs.getTimestamp(6).toLocalDateTime();
				String category = rs.getString(7);

				SubPost subPost = new SubPost(id, parentId,  getUserByUserName(username).getId(), title, message, postStamp, category);
				subPost.setUsername(username);
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
