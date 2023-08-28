package com.teampheonix.tplanguagemanagementapi.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import com.teampheonix.tplanguagemanagementapi.entity.LanguageContent;
import com.teampheonix.tplanguagemanagementapi.exception.ApiErrorCodes;
import com.teampheonix.tplanguagemanagementapi.exception.ApiException;
import com.teampheonix.tplanguagemanagementapi.model.LanguageContentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.teampheonix.tplanguagemanagementapi.repository.LanguageRepository;
import com.teampheonix.tplanguagemanagementapi.service.LanguageService;

@Service
public class LanguageServiceImpl implements LanguageService {
	
	@Autowired
	private LanguageRepository languageRepository;

	@Override
	public LanguageContent createContent(LanguageContentRequest languageContentRequest, String userId) {
		LanguageContent languageContent = new LanguageContent();
		languageContent.setPostId(languageContentRequest.getPostId());
		languageContent.setLanguage(languageContentRequest.getLanguage());
		languageContent.setContent(languageContentRequest.getContent());
        languageContent.setUserId(userId);
        languageContent.setCreatedBy(userId);
        languageContent.setCreatedDate(getCurrentDateTime());
		return languageRepository.save(languageContent);
	}

	@Override
	public LanguageContent getContentById(long contentId) {
		return languageRepository.findById(contentId)
				.orElseThrow(() -> new ApiException(ApiErrorCodes.CONTENT_NOT_FOUND));
	}

	@Override
	public LanguageContent getContentByLanguage(long postId, String language) {
		LanguageContent languageContent = languageRepository.findLanguageContentByPostIdAndLanguage(postId, language);
		if (languageContent == null) {
			throw new ApiException(ApiErrorCodes.CONTENT_NOT_FOUND);
		}
		return languageContent;
	}

	@Override
	public LanguageContent updateContent(LanguageContentRequest languageContentRequest, long contentId, String userId) {
		LanguageContent languageContent = languageRepository.findById(contentId)
				.orElseThrow(() -> new ApiException(ApiErrorCodes.CONTENT_NOT_FOUND));
		languageContent.setPostId(languageContentRequest.getPostId());
		languageContent.setLanguage(languageContentRequest.getLanguage());
		languageContent.setContent(languageContentRequest.getContent());
		languageContent.setUserId(userId);
		languageContent.setLastUpdatedBy(userId);
		languageContent.setLastUpdatedDate(getCurrentDateTime());
		return languageRepository.save(languageContent);
	}

	@Override
	public String deleteContent(long contentId) {
		Optional<LanguageContent> lang= languageRepository.findById(contentId);
		if (lang.isPresent()) {
			languageRepository.deleteById(contentId);
		}
		else {
			throw new ApiException(ApiErrorCodes.CONTENT_NOT_FOUND);
		}
		return "Language Content deleted successfully";
	}

	private LocalDateTime getCurrentDateTime() {
		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");
		String formattedDateTime = currentDateTime.format(formatter);
		return LocalDateTime.parse(formattedDateTime, formatter);
	}

}
