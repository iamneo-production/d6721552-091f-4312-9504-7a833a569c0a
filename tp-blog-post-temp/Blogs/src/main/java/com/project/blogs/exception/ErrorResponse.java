package com.project.blogs.exception;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
	    private int code;
	    private HttpStatus status;
	    private String message;
	    private String details;
		public int getCode() {
			return code;
		}
		public void setCode(int code) {
			this.code = code;
		}
		public HttpStatus getStatus() {
			return status;
		}
		public void setStatus(HttpStatus status) {
			this.status = status;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public String getDetails() {
			return details;
		}
		public void setDetails(String details) {
			this.details = details;
		}
		public ErrorResponse() {
			super();
			// TODO Auto-generated constructor stub
		}
		public ErrorResponse(int code, HttpStatus status, String message, String details) {
			super();
			this.code = code;
			this.status = status;
			this.message = message;
			this.details = details;
		}
	    

}