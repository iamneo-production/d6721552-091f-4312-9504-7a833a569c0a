package com.project.blogs.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	@NotBlank(message = "Username is REQUIRED")
	@Size(min = 3, message = "Atleast 3 letter")
	private String username;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = javax.persistence.CascadeType.REMOVE)
	@JsonIgnore
	private List<Post> posts;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = javax.persistence.CascadeType.REMOVE)
	@JsonIgnore
	private List<Comment> comments;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public User(
			@NotBlank(message = "Username is REQUIRED") @Size(min = 3, message = "Atleast 3 letter") String username,
			List<Post> posts, List<Comment> comments) {
		super();
		this.username = username;
		this.posts = posts;
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", posts=" + posts + ", comments=" + comments
				+ "]";
	}

	public User() {
		super();
	
	}
	

}
