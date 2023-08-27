package com.teampheonix.tpuserprofileapi.exception;

import lombok.Getter;
import static com.teampheonix.tpuserprofileapi.constants.ErrorConstants.INVALID_REQUEST_ERROR_CODE;
import static com.teampheonix.tpuserprofileapi.constants.ErrorConstants.UNKNOWN_ERROR_CODE;


@Getter
public class InvalidRequestException extends RuntimeException {

	private String errorCode;
    private String errorMessage;

    public InvalidRequestException(String errorMessage) {
        this.errorCode = INVALID_REQUEST_ERROR_CODE;
        this.errorMessage = errorMessage;
    }
}