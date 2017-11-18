package data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Post {
	private int postID; 
	private String title; 
	private String message; 
	private LocalDateTime postStamp; 
	private int userId;
	private String userName; 
	private String category; 
	private List<Post> replies; 
	
	public Post() {
		replies = new ArrayList<>(); 
	}
	public Post(int postID, String title, String message, LocalDateTime postStamp, int userId) {
		super();
		this.postID = postID;
		this.title = title;
		this.message = message;
		this.postStamp = postStamp;
		this.userId = userId; 
		replies = new ArrayList<>(); 

	}
	public int getPostID() {
		return postID;
	}

	public void setPostID(int postID) {
		this.postID = postID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getPostStamp() {
		return postStamp;
	}

	public void setPostStamp(LocalDateTime postStamp) {
		this.postStamp = postStamp;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<Post> getReplies() {
		return replies;
	}

	public void setReplies(List<Post> replies) {
		this.replies = replies;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + postID;
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
		Post other = (Post) obj;
		if (postID != other.postID)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Post [postID=" + postID + ", title=" + title + ", message=" + message + ", postStamp=" + postStamp
				+ "]";
	}
}
