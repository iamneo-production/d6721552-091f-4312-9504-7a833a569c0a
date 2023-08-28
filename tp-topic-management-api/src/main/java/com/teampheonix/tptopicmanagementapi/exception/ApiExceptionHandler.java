package com.teampheonix.tptopicmanagementapi.exception;

import com.teampheonix.tptopicmanagementapi.model.ErrorDto;
import com.teampheonix.tptopicmanagementapi.model.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {

    private final Map<ApiErrorCodes, ErrorDto> errorCodeMap;

    public ApiExceptionHandler() {
        this.errorCodeMap = registerErrorCodeMap();
    }

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<ResponseDto<Object>> handleTpException(ApiException ex) {
        log.error("Error - {}", ex.getCode());
        ResponseDto<Object> responseBody =
                ResponseDto.forError(Collections.singletonList(
                        this.errorCodeMap.getOrDefault(ex.getCode(), this.errorCodeMap.get(ApiErrorCodes.UNEXPECTED_ERROR))));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ResponseDto<Object>> handleRuntimeException(RuntimeException ex) {
        log.error("Unexpected Error - {}", ex.getMessage());
        ResponseDto<Object> responseBody =
                ResponseDto.forError(Collections.singletonList(this.errorCodeMap.get(ApiErrorCodes.UNEXPECTED_ERROR)));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }

    private Map<ApiErrorCodes, ErrorDto> registerErrorCodeMap() {
        Map<ApiErrorCodes,ErrorDto> errorCodeMap = new HashMap<>();
        errorCodeMap.put(ApiErrorCodes.UNAUTHORIZED_ERROR, new ErrorDto("3000", "Unauthorized Access"));
        errorCodeMap.put(ApiErrorCodes.UNAUTHORIZED_ACCESS_ERROR, new ErrorDto("3001", "You don't have required access to perform the operation"));
        errorCodeMap.put(ApiErrorCodes.INVALID_REQUEST, new ErrorDto("3002", "Invalid Request"));
        errorCodeMap.put(ApiErrorCodes.POST_NOT_FOUND, new ErrorDto("3003", "Post Not Found"));
        errorCodeMap.put(ApiErrorCodes.TOPIC_NOT_FOUND, new ErrorDto("3004", "Topic Not Found"));
        errorCodeMap.put(ApiErrorCodes.BLOG_POST_API_ERROR, new ErrorDto("3098", "Unexpected Error Occurred from Upstream API"));
        errorCodeMap.put(ApiErrorCodes.UNEXPECTED_ERROR, new ErrorDto("3099", "Unexpected Error Occurred!"));
        return errorCodeMap;
    }

}