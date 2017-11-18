package data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Post {
	protected int postID; 
	protected int userId;
	protected String title; 
	protected String message; 
	protected LocalDateTime postStamp; 
	protected String category;
	protected List<SubPost> replies; 
	
	public Post(int postID, int userId, String title, String message, LocalDateTime postOrigin, String category) {
		super();
		this.postID = postID;
		this.userId = userId; 
		this.title = title;
		this.message = message;
		this.postStamp = postOrigin;
		this.category = category; 
	}
	public Post() {
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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
		return "Post [postID=" + postID + ", userId=" + userId + ", title=" + title + ", message=" + message
				+ ", postStamp=" + postStamp + ", category=" + category + "]";
	}
}
