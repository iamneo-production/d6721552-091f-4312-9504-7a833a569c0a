package com.teampheonix.tplanguagemanagementapi.controller;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teampheonix.tplanguagemanagementapi.entity.Language;
import com.teampheonix.tplanguagemanagementapi.entity.ResponseDto;
import com.teampheonix.tplanguagemanagementapi.exceptions.ResourceNotFoundException;
import com.teampheonix.tplanguagemanagementapi.serviceImpl.LanguageServiceImpl;
//import com.google.cloud.translate.Translate;
//import com.google.cloud.translate.TranslateOptions;
//import com.google.cloud.translate.Translation;


//import com.google.cloud.translate.Translate;
//import com.google.cloud.translate.TranslateOptions;
//import com.google.cloud.translate.Translation;

@RestController
@RequestMapping("/api/language-management")
public class LanguageController {
	
//	/api/tp/language-management/content POST (use ESAPI encodeForHTML)
//	/api/tp/language-management/contents/{postId}?language="en" GET 
//	/api/tp/language-management/contents/{contentId} PUT
//	/api/tp/language-management/contents/{contentId} DELETE
	
	@Autowired
	LanguageServiceImpl languageServcie;
	
	@PostMapping("/content")
	public ResponseEntity<Language> createContent(@RequestBody Language lanuage){
		Language lang = languageServcie.createContent(lanuage);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(lang);
	}
	
//	@GetMapping("/contents/{postId}")
	@GetMapping("/contents/{contentId}")
	public ResponseEntity<ResponseDto<Optional<Language>>> getContent(@PathVariable int contentId,@RequestParam String language) throws ResourceNotFoundException{
//		try {
//			Optional<Language> lang = languageServcie.getContent(contentId);
//			ResponseEntity.status(HttpStatus.FOUND).body(lang);
//			return ResponseEntity.ok(ResponseDto.forSuccess(new Language()));
//		}
//		catch(NoSuchElementException e){
//			 List<ErrorDto> errorList = new ArrayList<>();
//			 ErrorDto errorDto1 = new ErrorDto("3001", "ContentId not found");
//	           errorList.add(errorDto1);
//			ResponseDto<Object> errorResponse = ResponseDto.forError(errorList);
//		     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
//		}
		
        
		return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.forSuccess(languageServcie.getContent(contentId)));
	}
	
	@PutMapping("/contents/{contentId}")
	public ResponseEntity<ResponseDto<Language>> updateContent(@RequestBody Language lanuage, @PathVariable int contentId ) throws ResourceNotFoundException{
		
		return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.forSuccess(languageServcie.updateContent(lanuage,contentId)));
	
	}
	
	@DeleteMapping("/contents/{contentId}")
	public ResponseEntity<String> deleteContent(@PathVariable int contentId ) throws ResourceNotFoundException{
		languageServcie.deleteContent(contentId);
		  return new ResponseEntity<String>("Content deleted successfully!!", HttpStatus.OK);
	}

}
