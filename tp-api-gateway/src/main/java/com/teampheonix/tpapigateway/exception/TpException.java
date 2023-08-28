package com.teampheonix.tpapigateway.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class TpException extends ResponseStatusException {

    public TpException(HttpStatusCode status, String reason) {
        super(status, reason);
    }

}
