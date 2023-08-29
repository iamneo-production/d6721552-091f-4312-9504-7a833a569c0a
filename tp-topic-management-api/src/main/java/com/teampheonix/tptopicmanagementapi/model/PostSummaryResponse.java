package com.teampheonix.tptopicmanagementapi.model;

import lombok.Data;

@Data
public class PostSummaryResponse {

	public Long postId;
	private String postName;
	private String userId;

}
