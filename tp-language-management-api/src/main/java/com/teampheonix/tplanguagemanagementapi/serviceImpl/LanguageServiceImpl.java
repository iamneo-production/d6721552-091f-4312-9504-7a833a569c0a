package com.teampheonix.tplanguagemanagementapi.serviceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

//import org.owasp.esapi.ESAPI;
//import org.owasp.esapi.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teampheonix.tplanguagemanagementapi.entity.Language;
import com.teampheonix.tplanguagemanagementapi.exceptions.ResourceNotFoundException;
import com.teampheonix.tplanguagemanagementapi.repository.LanguageRepository;
import com.teampheonix.tplanguagemanagementapi.service.LanguageService;

@Service
public class LanguageServiceImpl implements LanguageService{
	
	@Autowired
	LanguageRepository languagerepo;
	
//	private final Encoder esapiEncoder = ESAPI.encoder();


	public Language createContent(Language language) {
		

		LocalDateTime currentDateTime = LocalDateTime.now();

        // Define a DateTimeFormatter for 12-hour format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");

        // Format and print the current date and time in 12-hour format
        String formattedDateTime = currentDateTime.format(formatter);
        LocalDateTime localDateTime = LocalDateTime.parse(formattedDateTime, formatter);
        language.setCreated_date(localDateTime);
        String content = language.getContent().toLowerCase();
        language.setContent(content);
//		long currentTimeMillis = System.currentTimeMillis();
//        Timestamp timestamp = new Timestamp(currentTimeMillis);
		
//		language.setLast_updated_date(null);
//		esapiEncoder.encodeForHTML(language.getContent());
		return languagerepo.save(language);
	}

	public Optional<Language> getContent(int contentId) throws ResourceNotFoundException{
		Optional<Language> lang=languagerepo.findById(contentId);
//		Language language = languagerepo.findById(contentId).get();
//		String decodeContent =language.getContent();
		
		 if(lang.isPresent())  {
//			 language.setContent(decodeContent);
			 
			 return lang;
			}
		 else
			 throw new ResourceNotFoundException("contentId not found");
				
		
	}


	public Language updateContent(Language language, int contentId) throws ResourceNotFoundException{
		Optional<Language> lang=languagerepo.findById(contentId);
		if(lang.isPresent()) {
			LocalDateTime currentDateTime = LocalDateTime.now();

	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");

	        String formattedDateTime = currentDateTime.format(formatter);
	        LocalDateTime localDateTime = LocalDateTime.parse(formattedDateTime, formatter);
	        language.setLast_updated_date(localDateTime);
			return languagerepo.save(language);
			}
		
		else
			
			throw new ResourceNotFoundException("updation failed! contentId not found");
	}

	public String deleteContent(int contentId) throws ResourceNotFoundException{
		
		Optional<Language> lang=languagerepo.findById(contentId);
		if(lang.isPresent()) {
			languagerepo.deleteById(contentId);;
			}
		else
			throw new ResourceNotFoundException("deletion failed! contentId not found");
		return "deleted successfully";
			
	}

}
