package com.teampheonix.tptopicmanagementapi.client;

import com.teampheonix.tptopicmanagementapi.exception.ApiErrorCodes;
import com.teampheonix.tptopicmanagementapi.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class BlogPostClientApiFallbackFactory implements FallbackFactory<BlogPostClientApi>  {

    @Override
    public BlogPostClientApi create(Throwable cause) {
        return new BlogPostClientApi() {

            private ApiException handleApiException() {
                if (cause instanceof ApiException) {
                    return new ApiException(((ApiException) cause).getCode());
                }
                log.error("Error occurred while calling Blog Post API - Cause", cause);
                return new ApiException(ApiErrorCodes.BLOG_POST_API_ERROR);
            }
        };
    }

}
