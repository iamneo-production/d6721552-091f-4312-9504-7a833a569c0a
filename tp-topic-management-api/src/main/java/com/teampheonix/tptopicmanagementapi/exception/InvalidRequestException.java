package com.teampheonix.tptopicmanagementapi.exception;
import static com.teampheonix.tptopicmanagementapi.constants.ErrorConstants.*;

import lombok.Getter;


@Getter
public class InvalidRequestException extends RuntimeException {

	private String errorCode;
	private String errorMessage;

	public InvalidRequestException(String errorMessage) {

		this.errorCode = INVALID_REQUEST_ERROR_CODE;
		this.errorMessage = errorMessage;
	}

}