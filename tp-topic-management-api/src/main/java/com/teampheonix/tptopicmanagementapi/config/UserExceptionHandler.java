package com.teampheonix.tptopicmanagementapi.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import java.util.Arrays;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.teampheonix.tptopicmanagementapi.exception.InvalidRequestException;
import com.teampheonix.tptopicmanagementapi.model.ErrorDto;
import com.teampheonix.tptopicmanagementapi.model.ResponseDto;
import static com.teampheonix.tptopicmanagementapi.constants.ErrorConstants.*;


@ControllerAdvice
public class UserExceptionHandler {
	@ExceptionHandler(value = InvalidRequestException.class)
	public ResponseEntity<ResponseDto<Object>> handleInvalidRequestException(InvalidRequestException ex) {
		ErrorDto error = new ErrorDto(ex.getErrorCode(), ex.getErrorMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseDto.forError(Arrays.asList(error)));
	}

	@ExceptionHandler(value = RuntimeException.class)
	public ResponseEntity<ResponseDto<Object>> handleRuntimeException(RuntimeException ex) {
		ErrorDto error = new ErrorDto(UNKNOWN_ERROR_CODE, ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseDto.forError(Arrays.asList(error)));
	}

}