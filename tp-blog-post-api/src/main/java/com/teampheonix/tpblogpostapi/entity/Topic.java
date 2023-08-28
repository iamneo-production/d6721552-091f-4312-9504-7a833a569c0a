package com.teampheonix.tpblogpostapi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Topic {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long topicId;

}
