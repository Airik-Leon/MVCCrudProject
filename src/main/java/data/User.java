package data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class User {
	private int id; 
	private String firstName;
	private String lastName; 
	private String userName; 
	private String password; 
	private LocalDate accountOrigin;
	private Map<Integer, Post> posts; 
	public User() {
		
	}
	public User(int id, String firstName, String lastName,  String userName, String password, LocalDate accountOrigin) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName; 
		this.userName = userName;
		this.password = password;
		this.accountOrigin = accountOrigin;
	}
	
	public User(int id, String firstName, String lastName, String userName, String password, LocalDate accountOrigin, Map<Integer,Post> posts) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName; 
		this.userName = userName;
		this.password = password;
		this.accountOrigin = accountOrigin;
		this.posts = posts;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String name) {
		this.firstName = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LocalDate getAccountOrigin() {
		return accountOrigin;
	}
	public void setAccountOrigin(LocalDate accountOrigin) {
		this.accountOrigin = accountOrigin;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Post>getPostsAsList() {
		return new ArrayList<Post>(posts.values());
	}
	public Map<Integer, Post> getPosts(){
		return this.posts; 
	}
	public void setPosts(Map<Integer, Post> posts) {
		this.posts = posts;
	}
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName
				+ ", password=" + password + ", accountOrigin=" + accountOrigin + ", posts=" + posts + "]";
	}

}
