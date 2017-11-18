package data;
import java.time.LocalDateTime;
import javax.validation.constraints.Size;

public class User {
	private int id; 
	@Size(min=2, max=20)
	private String firstName;
	@Size(min=2, max=20)
	private String lastName; 
	@Size(min=5, max=10, message="user names must be at least 5 characters")
	private String userName; 
	@Size(min=8, max=20, message="password must be at least 8 characters")
	private String password; 
	private LocalDateTime accountOrigin;
	int role; 
	
	public User() {
	}
	public User(int id, String firstName, String lastName,  String userName, String password, LocalDateTime accountOrigin, int role) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName; 
		this.userName = userName;
		this.password = password;
		this.accountOrigin = accountOrigin;
		this.role = role;
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
	public LocalDateTime getAccountOrigin() {
		return accountOrigin;
	}
	public void setAccountOrigin(LocalDateTime accountOrigin) {
		this.accountOrigin = accountOrigin;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public boolean isAdmin() {
		if(role == 1) {
			return true;
		}
		else {
			return false;		
		}
	}
	public int getRole() {
		return this.role;
	}
	public void setRole(int role) {
		this.role = role;
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
				+ ", password=" + password + ", accountOrigin=" + accountOrigin + ", role=" + role + "]";
	}
	
}
