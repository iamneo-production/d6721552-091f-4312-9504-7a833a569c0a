package com.project.blogs.exception;

public class ApiException extends RuntimeException {
	    private ErrorCodes errorCode;

	    public ApiException(ErrorCodes errorCode) {
	        super(errorCode.getMessage());
	        this.errorCode = errorCode;
	    }

	    public ErrorCodes getErrorCode() {
	        return errorCode;
	    }
	}