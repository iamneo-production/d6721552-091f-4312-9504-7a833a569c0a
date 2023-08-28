package com.teampheonix.tpblogpostapi.client;

import com.teampheonix.tpblogpostapi.exception.ApiErrorCodes;
import com.teampheonix.tpblogpostapi.exception.ApiException;
import com.teampheonix.tpblogpostapi.model.ResponseDto;
import com.teampheonix.tpblogpostapi.model.request.LanguageContentRequest;
import com.teampheonix.tpblogpostapi.model.response.LanguageContentResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class LanguageManagementClientApiFallbackFactory implements FallbackFactory<LanguageManagementClientApi>  {

    @Override
    public LanguageManagementClientApi create(Throwable cause) {
        return new LanguageManagementClientApi() {

            @Override
            public ResponseDto<LanguageContentResponse> createContent(LanguageContentRequest contentRequest, String apiKey, String userId, String roles) {
                throw handleApiException();
            }

            @Override
            public ResponseDto<LanguageContentResponse> getContentByLanguage(long postId, String language, String apiKey, String userId, String roles) {
                throw handleApiException();
            }

            @Override
            public ResponseDto<List<LanguageContentResponse>> getContentsByPostId(long postId, String apiKey, String userId, String roles) {
                throw handleApiException();
            }

            @Override
            public ResponseDto<List<LanguageContentResponse>> getAllContents(String apiKey, String userId, String roles) {
                throw handleApiException();
            }

            private ApiException handleApiException() {
                if (cause instanceof ApiException) {
                    return new ApiException(((ApiException) cause).getCode());
                }
                log.error("Error occurred while calling Language Management API - Cause", cause);
                return new ApiException(ApiErrorCodes.LANGUAGE_MANAGEMENT_API_ERROR);
            }
        };
    }

}
