package com.teampheonix.tpblogpostapi.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostSummaryResponse {

	public Long postId;
	private String postName;
	private String userId;

}
