package com.teampheonix.tptopicmanagementapi.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teampheonix.tptopicmanagementapi.exception.ApiErrorCodes;
import com.teampheonix.tptopicmanagementapi.exception.ApiException;
import com.teampheonix.tptopicmanagementapi.model.ResponseDto;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ClientApiErrorDecoder implements ErrorDecoder {

    private final Map<String, ApiErrorCodes> errorCodeMap;

    public ClientApiErrorDecoder() {
        this.errorCodeMap = registerErrorCodeMap();
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        Response.Body responseBody = response.body();
        HttpStatus responseStatus = HttpStatus.valueOf(response.status());

        if (responseBody != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            String clientErrorCode = "1099";
            try {
                ResponseDto<?> errorResponse = objectMapper.readValue(responseBody.asInputStream(), ResponseDto.class);
                if (errorResponse != null && errorResponse.getErrors() != null && errorResponse.getErrors().size() > 0) {
                    clientErrorCode = errorResponse.getErrors().get(0).getCode();
                }
            } catch (Exception e) {
                log.error(e.getMessage());
                throw new ApiException(ApiErrorCodes.BLOG_POST_API_ERROR);
            }
            ApiErrorCodes errorCode = this.errorCodeMap.get(clientErrorCode);
            return new ApiException(errorCode);
        }
        if (responseStatus.isSameCodeAs(HttpStatus.UNAUTHORIZED) || responseStatus.isSameCodeAs(HttpStatus.FORBIDDEN)) {
            return new ApiException(ApiErrorCodes.UNAUTHORIZED_ERROR);
        }
        else {
            return new ApiException(ApiErrorCodes.BLOG_POST_API_ERROR);
        }
    }

    private Map<String, ApiErrorCodes> registerErrorCodeMap() {
        Map<String, ApiErrorCodes> errorCodeMap = new HashMap<>();
        errorCodeMap.put("2000", ApiErrorCodes.UNAUTHORIZED_ERROR);
        errorCodeMap.put("2001", ApiErrorCodes.UNAUTHORIZED_ACCESS_ERROR);
        errorCodeMap.put("2002", ApiErrorCodes.INVALID_REQUEST);
        errorCodeMap.put("4099", ApiErrorCodes.BLOG_POST_API_ERROR);
        return errorCodeMap;
    }

}
