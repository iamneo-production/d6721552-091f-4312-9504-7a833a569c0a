package com.teampheonix.tplanguagemanagementapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name="language_content")
@Data
public class LanguageContent {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
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
