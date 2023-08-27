package com.project.blogs.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCodes {
    USER_NOT_FOUND(1000, HttpStatus.NOT_FOUND, "User not found"),
    POST_NOT_FOUND(2000, HttpStatus.NOT_FOUND, "Post not found"),
    COMMENT_NOT_FOUND(3000, HttpStatus.NOT_FOUND, "Post not found"),
    USER_ALREADY_EXISTS(1001, HttpStatus.NOT_FOUND, "User already exists")
    ;

    private final int code;
    private final HttpStatus status;
    private final String message;

	ErrorCodes(int code, HttpStatus status, String message) {
		    this.code = code;
	        this.status = status;
	        this.message = message;
	}
    public int getCode() {
        return code;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
