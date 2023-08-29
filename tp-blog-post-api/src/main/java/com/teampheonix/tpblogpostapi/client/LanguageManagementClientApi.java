package com.teampheonix.tpblogpostapi.client;

import com.teampheonix.tpblogpostapi.config.FeignClientConfig;
import com.teampheonix.tpblogpostapi.model.ResponseDto;
import com.teampheonix.tpblogpostapi.model.request.LanguageContentRequest;
import com.teampheonix.tpblogpostapi.model.response.LanguageContentResponse;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@FeignClient(name = "tp-language-management-api",
            configuration = FeignClientConfig.class,
            fallbackFactory = LanguageManagementClientApiFallbackFactory.class)
public interface LanguageManagementClientApi {

    @PostMapping("/api/language-management/content")
    ResponseDto<LanguageContentResponse> createContent(@RequestBody LanguageContentRequest contentRequest,
                                                       @RequestHeader("API_KEY") String apiKey,
                                                       @RequestHeader("USER_ID") String userId,
                                                       @RequestHeader("CLAIMS") String roles);

    @GetMapping("/api/language-management/contents/{postId}")
    ResponseDto<LanguageContentResponse> getContentByLanguage(@PathVariable("postId") long postId,
                                                            @RequestParam("language") String language,
                                                            @RequestHeader("API_KEY") String apiKey,
                                                            @RequestHeader("USER_ID") String userId,
                                                            @RequestHeader("CLAIMS") String roles);

    @GetMapping("/api/language-management/contents/post/{postId}")
    ResponseDto<List<LanguageContentResponse>> getContentsByPostId(@PathVariable("postId") long postId,
                                                            @RequestHeader("API_KEY") String apiKey,
                                                            @RequestHeader("USER_ID") String userId,
                                                            @RequestHeader("CLAIMS") String roles);

    @GetMapping("/api/language-management/contents/all")
    ResponseDto<List<LanguageContentResponse>> getAllContents(@RequestHeader("API_KEY") String apiKey,
                                                              @RequestHeader("USER_ID") String userId,
                                                              @RequestHeader("CLAIMS") String roles);

    @DeleteMapping("/api/language-management/post/{postId}/contents")
    ResponseDto<String> deleteContentsByPostId(@PathVariable("postId") long postId,
                                              @RequestHeader("API_KEY") String apiKey,
                                              @RequestHeader("USER_ID") String userId,
                                              @RequestHeader("CLAIMS") String roles);

}
