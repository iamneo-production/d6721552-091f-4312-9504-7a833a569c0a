package com.teampheonix.tptopicmanagementapi.client;

import com.teampheonix.tptopicmanagementapi.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@FeignClient(name = "tp-language-management-api",
            configuration = FeignClientConfig.class,
            fallbackFactory = BlogPostClientApiFallbackFactory.class)
public interface BlogPostClientApi {

//    @PostMapping("/api/language-management/content")
//    ResponseDto<LanguageContentResponse> createContent(@RequestBody LanguageContentRequest contentRequest,
//                                                       @RequestHeader("API_KEY") String apiKey,
//                                                       @RequestHeader("USER_ID") String userId,
//                                                       @RequestHeader("CLAIMS") String roles);

}
