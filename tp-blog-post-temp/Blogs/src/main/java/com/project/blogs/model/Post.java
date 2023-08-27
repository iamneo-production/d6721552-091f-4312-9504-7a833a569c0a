package com.project.blogs.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.persistence.JoinColumn;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "posts")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int postId;

	@NotBlank(message = "Category can't be empty")
	@Size(min = 3, message = "minimum 3 letter required")
	private String postName;

	@NotBlank(message = "Language can't be empty")
	@Size(min = 3, message = "minimum 3 letter required")
	private String language;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Comment> comments;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private User user;

	   @ManyToMany(cascade = CascadeType.ALL)
	    @JoinTable(
	        name = "post_topic",
	        joinColumns = @JoinColumn(name = "post_id"),
	        inverseJoinColumns = @JoinColumn(name = "topic_id")
	    )
	    private Set<Topic> topics;

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Topic> getTopics() {
		return topics;
	}

	public void setTopics(Set<Topic> topics) {
		this.topics = topics;
	}

	public Post() {
		super();
	}

	@Override
	public String toString() {
		return "Post [postId=" + postId + ", postName=" + postName + ", language=" + language + ", comments=" + comments
				+ ", user=" + user + ", topics=" + topics + "]";
	}

	public Post(
			@NotBlank(message = "Category can't be empty") @Size(min = 3, message = "minimum 3 letter required") String postName,
			@NotBlank(message = "Language can't be empty") @Size(min = 3, message = "minimum 3 letter required") String language,
			List<Comment> comments, User user, Set<Topic> topics) {
		super();
		this.postName = postName;
		this.language = language;
		this.comments = comments;
		this.user = user;
		this.topics = topics;
	}
	   

}
