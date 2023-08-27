package com.teampheonix.tplanguagemanagementapi.service;

import java.util.Optional;

import com.teampheonix.tplanguagemanagementapi.entity.Language;
import com.teampheonix.tplanguagemanagementapi.exceptions.ResourceNotFoundException;

public interface LanguageService {
	
	Language createContent(Language lanuage);
	
	Optional<Language> getContent(int contentId) throws ResourceNotFoundException;
	
	Language updateContent(Language language, int contentId) throws ResourceNotFoundException;
	
	String deleteContent(int contentId) throws ResourceNotFoundException;

}
