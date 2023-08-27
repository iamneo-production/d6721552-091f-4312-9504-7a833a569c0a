package com.teampheonix.tplanguagemanagementapi.exceptions;

import static com.teampheonix.tplanguagemanagementapi.constants.ErrorConstants.CONTENT_ERROR_CODE;

public class ResourceNotFoundException extends RuntimeException{
	
	 private String errorCode;
	    private String errorMessage;

	    public ResourceNotFoundException(String errorMessage) {
	        this.errorCode = CONTENT_ERROR_CODE;
	        this.errorMessage = errorMessage;
	    }
	    
	    

	public String getErrorCode() {
			return errorCode;
		}



		public void setErrorCode(String errorCode) {
			this.errorCode = errorCode;
		}



		public String getErrorMessage() {
			return errorMessage;
		}



		public void setErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
		}



	@Override
	public String toString() {
		return "ResourceNotFoundException [errorCode=" + errorCode + ", errorMessage=" + errorMessage + "]";
	}
	 
	 

}
