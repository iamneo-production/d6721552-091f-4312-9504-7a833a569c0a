package com.teampheonix.tpblogpostapi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "comments")
@Data
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long commentId;
	private String comment;
	private String userId;

}
