package com.teampheonix.tpblogpostapi.entity;

import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "post")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long postId;

	private String postName;
	private String language;
	private String userId;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Comment> comments;

   	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
		name = "post_topic",
		joinColumns = @JoinColumn(name = "post_id"),
		inverseJoinColumns = @JoinColumn(name = "topic_id")
	)
	private List<Topic> topics;

}
