package com.teampheonix.tptopicmanagementapi.client;

import com.teampheonix.tptopicmanagementapi.config.FeignClientConfig;
import com.teampheonix.tptopicmanagementapi.model.PostSummaryResponse;
import com.teampheonix.tptopicmanagementapi.model.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@FeignClient(name = "tp-blog-post-api",
            configuration = FeignClientConfig.class,
            fallbackFactory = BlogPostClientApiFallbackFactory.class)
public interface BlogPostClientApi {

    @PutMapping("/api/blog-post/post/{postId}/topic/{topicId}")
    ResponseDto<PostSummaryResponse> addPostToTopic(@PathVariable("postId") long postId,
                                                   @PathVariable("topicId") long topicId,
                                                   @RequestHeader("API_KEY") String apiKey,
                                                   @RequestHeader("USER_ID") String userId,
                                                   @RequestHeader("CLAIMS") String roles);

    @DeleteMapping("/api/blog-post/post/{postId}/topic/{topicId}")
    ResponseDto<String> removePostFromTopic(@PathVariable("postId") long postId,
                                                   @PathVariable("topicId") long topicId,
                                                   @RequestHeader("API_KEY") String apiKey,
                                                   @RequestHeader("USER_ID") String userId,
                                                   @RequestHeader("CLAIMS") String roles);

    @DeleteMapping("/api/blog-post/posts/topics/{topicId}")
    ResponseDto<String> deleteTopicPosts(@PathVariable("topicId") long topicId,
                                                     @RequestHeader("API_KEY") String apiKey,
                                                     @RequestHeader("USER_ID") String userId,
                                                     @RequestHeader("CLAIMS") String roles);

    @GetMapping("/api/blog-post/posts/topics/{topicId}")
    ResponseDto<List<PostSummaryResponse>> getPostsByTopicId(@PathVariable("topicId") long topicId,
                                                     @RequestHeader("API_KEY") String apiKey,
                                                     @RequestHeader("USER_ID") String userId,
                                                     @RequestHeader("CLAIMS") String roles);

}
