package data;

import java.time.LocalDateTime;

public class SubPost extends Post{
	private int parentId; 
	
	public SubPost(int postID,int parentId,  int userId, String title, String message, LocalDateTime postOrigin, String category) {
		super();
		this.postID = postID;
		this.parentId = parentId; 
		this.userId = userId; 
		this.title = title;
		this.message = message;
		this.postStamp = postOrigin;
		this.category = category; 
	}
	public SubPost() {
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
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
		SubPost other = (SubPost) obj;
		if (postID != other.postID)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "SubPost [postID=" + postID + ", parentId=" + parentId + ", userId=" + userId + ", title=" + title
				+ ", message=" + message + ", postStamp=" + postStamp + ", category=" + category + "]";
	}
}

