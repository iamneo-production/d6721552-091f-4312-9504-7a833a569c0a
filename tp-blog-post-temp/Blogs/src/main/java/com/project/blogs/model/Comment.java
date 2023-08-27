package com.project.blogs.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "comment")
public class Comment {
	@Id
	@GeneratedValue
	private int commentid;

	@NotBlank(message = "Comments can't be empty")
	@Size(min = 3, message = "minimum 3 letter required")
	private String userComment;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@JoinColumn
	private Post post;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private User user;

	public String getUserComment() {
		return userComment;
	}

	public void setUserComment(String userComment) {
		this.userComment = userComment;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Comment [commentid=" + commentid + ", userComment=" + userComment + ", post=" + post + ", user=" + user
				+ "]";
	}

	public Comment() {
		super();
	}

	public int getCommentid() {
		return commentid;
	}

	public void setCommentid(int commentid) {
		this.commentid = commentid;
	}

	public Comment(
			@NotBlank(message = "Comments can't be empty") @Size(min = 3, message = "minimum 3 letter required") String userComment,
			Post post, User user) {
		super();
		this.userComment = userComment;
		this.post = post;
		this.user = user;
	}
	

}
