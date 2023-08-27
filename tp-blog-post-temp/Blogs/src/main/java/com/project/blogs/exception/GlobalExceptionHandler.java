package com.project.blogs.exception;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
	@ControllerAdvice
	public class GlobalExceptionHandler {
	    @ExceptionHandler(ApiException.class)
	    public ResponseEntity<ErrorResponse> handleAPIException(ApiException ex) {
	        ErrorResponse errorResponse = new ErrorResponse();
	        errorResponse.setCode(ex.getErrorCode().getCode());
	        errorResponse.setStatus(ex.getErrorCode().getStatus());
	        errorResponse.setMessage(ex.getErrorCode().getMessage());
	        errorResponse.setDetails(ex.getMessage()); // You can customize this based on your needs
	        return new ResponseEntity<>(errorResponse, ex.getErrorCode().getStatus());
	    }
	}


