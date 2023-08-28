package com.teampheonix.tpblogpostapi.model.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LanguageContentResponse {

	private Long languageContentId;
	private Long postId;
	private String userId;
	private String content;
	private String language;
	private String createdBy;
	private LocalDateTime createdDate;
	private String lastUpdatedBy;
	private LocalDateTime lastUpdatedDate;

}
