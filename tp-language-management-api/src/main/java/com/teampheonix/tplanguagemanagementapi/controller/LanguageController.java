package com.teampheonix.tplanguagemanagementapi.controller;

import com.teampheonix.tplanguagemanagementapi.aspect.AuthorizeRoles;
import com.teampheonix.tplanguagemanagementapi.aspect.RolesConstants;
import com.teampheonix.tplanguagemanagementapi.entity.LanguageContent;
import com.teampheonix.tplanguagemanagementapi.exception.ApiErrorCodes;
import com.teampheonix.tplanguagemanagementapi.exception.ApiException;
import com.teampheonix.tplanguagemanagementapi.model.LanguageContentRequest;
import com.teampheonix.tplanguagemanagementapi.service.LanguageService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.teampheonix.tplanguagemanagementapi.model.ResponseDto;

@RestController
@RequestMapping("/api/language-management")
public class LanguageController {

	@Autowired
	private LanguageService languageService;
	
	@PostMapping("/content")
	@AuthorizeRoles(roles = { RolesConstants.ROLES_ADMIN, RolesConstants.ROLES_BLOGGER,
			RolesConstants.ROLES_CONTENT_MODERATOR, RolesConstants.ROLES_TRANSLATOR })
	public ResponseEntity<LanguageContent> createContent(HttpServletRequest request,
														 @RequestBody LanguageContentRequest languageContentRequest){
		String userId = request.getHeader("USER_ID");
		validateLanguageContentRequest(languageContentRequest);
		LanguageContent content = languageService.createContent(languageContentRequest, userId);
		return ResponseEntity.status(HttpStatus.CREATED).body(content);
	}

	@GetMapping("/contents/{contentId}")
	@AuthorizeRoles(roles = { RolesConstants.ROLES_ADMIN, RolesConstants.ROLES_CONTENT_MODERATOR })
	public ResponseEntity<ResponseDto<LanguageContent>> getContent(@PathVariable long contentId) {
		return ResponseEntity.status(HttpStatus.OK).body(
				ResponseDto.forSuccess(languageService.getContentById(contentId)));
	}

	@GetMapping("/contents/{postId}")
	public ResponseEntity<ResponseDto<LanguageContent>> getContentByLanguage(
			@PathVariable("postId") long postId, @RequestParam("language") String language) {
		return ResponseEntity.status(HttpStatus.OK).body(
				ResponseDto.forSuccess(languageService.getContentByLanguage(postId, language)));
	}
	
	@PutMapping("/contents/{contentId}")
	@AuthorizeRoles(roles = { RolesConstants.ROLES_ADMIN, RolesConstants.ROLES_BLOGGER,
			RolesConstants.ROLES_CONTENT_MODERATOR, RolesConstants.ROLES_TRANSLATOR })
	public ResponseEntity<ResponseDto<LanguageContent>> updateContent(HttpServletRequest request,
																	  @RequestBody LanguageContentRequest languageContentRequest,
																	  @PathVariable long contentId ) {
		String userId = request.getHeader("USER_ID");
		validateLanguageContentRequest(languageContentRequest);
		return ResponseEntity.status(HttpStatus.OK).body(
				ResponseDto.forSuccess(languageService.updateContent(languageContentRequest,contentId, userId)));
	}
	
	@DeleteMapping("/contents/{contentId}")
	@AuthorizeRoles(roles = { RolesConstants.ROLES_ADMIN, RolesConstants.ROLES_BLOGGER,
			RolesConstants.ROLES_CONTENT_MODERATOR })
	public ResponseEntity<String> deleteContent(@PathVariable long contentId ) {
		languageService.deleteContent(contentId);
		  return new ResponseEntity<String>("Content deleted successfully!!", HttpStatus.OK);
	}

	private void validateLanguageContentRequest(LanguageContentRequest request) {
		if (StringUtils.isBlank(request.getLanguage())
				|| StringUtils.isBlank(request.getContent())
				|| request.getPostId() == null || request.getPostId() < 1) {
			throw new ApiException(ApiErrorCodes.INVALID_REQUEST);
		}
	}

}
