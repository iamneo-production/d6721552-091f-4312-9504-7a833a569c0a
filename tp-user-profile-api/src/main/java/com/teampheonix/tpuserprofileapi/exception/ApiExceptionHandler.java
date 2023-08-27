package com.teampheonix.tpuserprofileapi.exception;

import com.teampheonix.tpuserprofileapi.model.ErrorDto;
import com.teampheonix.tpuserprofileapi.model.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {

    private final Map<TpErrorCodes, ErrorDto> errorCodeMap;

    public ApiExceptionHandler() {
        this.errorCodeMap = registerErrorCodeMap();
    }

    @ExceptionHandler(value = TpException.class)
    public ResponseEntity<ResponseDto<Object>> handleTpException(TpException ex) {
        log.error("Error - {}", ex.getCode());
        ResponseDto<Object> responseBody =
                ResponseDto.forError(Collections.singletonList(
                        this.errorCodeMap.getOrDefault(ex.getCode(), this.errorCodeMap.get(TpErrorCodes.UNEXPECTED_ERROR))));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseDto<Object>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.error("JSON Parse Error - {}", ex.getMessage());
        ResponseDto<Object> responseBody =
                ResponseDto.forError(Collections.singletonList(this.errorCodeMap.get(TpErrorCodes.INVALID_ROLE)));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ResponseDto<Object>> handleRuntimeException(RuntimeException ex) {
        log.error("Unexpected Error - {}", ex.getMessage());
        ResponseDto<Object> responseBody =
                ResponseDto.forError(Collections.singletonList(this.errorCodeMap.get(TpErrorCodes.UNEXPECTED_ERROR)));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }

    private Map<TpErrorCodes,ErrorDto> registerErrorCodeMap() {
        Map<TpErrorCodes,ErrorDto> errorCodeMap = new HashMap<>();
        errorCodeMap.put(TpErrorCodes.UNAUTHORIZED_ERROR, new ErrorDto("1000", "Unauthorized Access"));
        errorCodeMap.put(TpErrorCodes.UNAUTHORIZED_ACCESS_ERROR, new ErrorDto("1001", "You don't have required access to perform the operation"));
        errorCodeMap.put(TpErrorCodes.INVALID_REQUEST, new ErrorDto("1002", "Invalid Request"));
        errorCodeMap.put(TpErrorCodes.INVALID_USERNAME_OR_PASSWORD, new ErrorDto("1003", "Invalid Username or Password"));
        errorCodeMap.put(TpErrorCodes.INVALID_ROLE, new ErrorDto("1004", "Invalid Role"));
        errorCodeMap.put(TpErrorCodes.INVALID_USER_ID, new ErrorDto("1005", "Invalid User ID"));
        errorCodeMap.put(TpErrorCodes.USER_ALREADY_EXISTS, new ErrorDto("1006", "User already exists"));
        errorCodeMap.put(TpErrorCodes.UNEXPECTED_ERROR, new ErrorDto("1099", "Unexpected Error Occurred!"));
        return errorCodeMap;
    }

}