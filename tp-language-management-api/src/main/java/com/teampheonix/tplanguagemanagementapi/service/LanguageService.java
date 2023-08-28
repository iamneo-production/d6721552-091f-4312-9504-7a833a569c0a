package com.teampheonix.tplanguagemanagementapi.service;

import com.teampheonix.tplanguagemanagementapi.entity.LanguageContent;
import com.teampheonix.tplanguagemanagementapi.model.LanguageContentRequest;

import java.util.List;

public interface LanguageService {
	
	LanguageContent createContent(LanguageContentRequest languageContentRequest, String userId);
	LanguageContent getContentById(long contentId);
	LanguageContent getContentByLanguage(long postId, String language);
	List<LanguageContent> getContentsByPostId(long postId);
	List<LanguageContent> getAllContents();
	LanguageContent updateContent(LanguageContentRequest languageContentRequest, long contentId, String userId);
	String deleteContent(long contentId);

}
