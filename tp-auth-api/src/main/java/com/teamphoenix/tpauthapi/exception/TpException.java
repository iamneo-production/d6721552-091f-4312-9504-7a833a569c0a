package com.teamphoenix.tpauthapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TpException extends RuntimeException {

    private final TpErrorCodes code;
    
}
