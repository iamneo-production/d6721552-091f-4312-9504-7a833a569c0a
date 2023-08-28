package com.teampheonix.tpuserprofileapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiException extends RuntimeException {

    private final ApiErrorCodes code;
    
}
