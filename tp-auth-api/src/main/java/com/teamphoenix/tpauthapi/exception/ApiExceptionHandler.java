package com.teamphoenix.tpauthapi.exception;

import com.teamphoenix.tpauthapi.exception.TpErrorCodes;
import com.teamphoenix.tpauthapi.exception.TpException;
import com.teamphoenix.tpauthapi.model.ErrorDto;
import com.teamphoenix.tpauthapi.model.ResponseDto;

import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {

    private final Map<TpErrorCodes,ErrorDto> errorCodeMap;

    public ApiExceptionHandler() {
        this.errorCodeMap = registerErrorCodeMap();
    }

    @ExceptionHandler(value = TpException.class)
    public ResponseEntity<ResponseDto<Object>> handleTpException(TpException ex) {
        log.error("Error - {}", ex.getMessage());
        ResponseDto<Object> responseBody =
                ResponseDto.forError(Collections.singletonList(
                        this.errorCodeMap.getOrDefault(ex.getCode(), this.errorCodeMap.get(TpErrorCodes.UNEXPECTED_ERROR))));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }

    @ExceptionHandler(value = SignatureException.class)
    public ResponseEntity<ResponseDto<Object>> handleSignatureException(SignatureException ex) {
        log.error("Token Error - {}", ex.getMessage());
        ResponseDto<Object> responseBody =
                ResponseDto.forError(Collections.singletonList(this.errorCodeMap.get(TpErrorCodes.INVALID_TOKEN)));
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ResponseDto<Object>> handleRuntimeException(Exception ex) {
        log.error("Unexpected Error - {}", ex.getMessage());
        ResponseDto<Object> responseBody =
                ResponseDto.forError(Collections.singletonList(this.errorCodeMap.get(TpErrorCodes.UNEXPECTED_ERROR)));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }

    private Map<TpErrorCodes,ErrorDto> registerErrorCodeMap() {
        Map<TpErrorCodes,ErrorDto> errorCodeMap = new HashMap<>();
        errorCodeMap.put(TpErrorCodes.UNAUTHORIZED_ERROR, new ErrorDto("5000", "Unauthorized Access"));
        errorCodeMap.put(TpErrorCodes.UNAUTHORIZED_ACCESS_ERROR, new ErrorDto("5001", "You don't have required access to perform the operation"));
        errorCodeMap.put(TpErrorCodes.INVALID_REQUEST, new ErrorDto("5002", "Invalid Request"));
        errorCodeMap.put(TpErrorCodes.INVALID_USERNAME_OR_PASSWORD, new ErrorDto("5003", "Invalid Username or Password"));
        errorCodeMap.put(TpErrorCodes.INVALID_TOKEN, new ErrorDto("5004", "Invalid Token"));
        errorCodeMap.put(TpErrorCodes.USER_PROFILE_API_ERROR, new ErrorDto("5098", "Unexpected Error Occurred in Upstream API"));
        errorCodeMap.put(TpErrorCodes.UNEXPECTED_ERROR, new ErrorDto("5099", "Unexpected Error Occurred!"));
        return errorCodeMap;
    }

}