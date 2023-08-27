package com.teamphoenix.tpauthapi.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamphoenix.tpauthapi.exception.TpErrorCodes;
import com.teamphoenix.tpauthapi.exception.TpException;
import com.teamphoenix.tpauthapi.model.ResponseDto;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ClientApiErrorDecoder implements ErrorDecoder {

    private final Map<String,TpErrorCodes> errorCodeMap;

    public ClientApiErrorDecoder() {
        this.errorCodeMap = registerErrorCodeMap();
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        Response.Body responseBody = response.body();
        HttpStatus responseStatus = HttpStatus.valueOf(response.status());

        if (responseBody != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            String clientErrorCode = "1099";
            try {
                ResponseDto<?> errorResponse = objectMapper.readValue(responseBody.asInputStream(), ResponseDto.class);
                if (errorResponse != null && errorResponse.getErrors() != null && errorResponse.getErrors().size() > 0) {
                    clientErrorCode = errorResponse.getErrors().get(0).getCode();
                }
            } catch (Exception e) {
                log.error(e.getMessage());
                throw new TpException(TpErrorCodes.USER_PROFILE_API_ERROR);
            }
            TpErrorCodes errorCode = this.errorCodeMap.get(clientErrorCode);
            return new TpException(errorCode);
        }
        if (responseStatus.isSameCodeAs(HttpStatus.UNAUTHORIZED) || responseStatus.isSameCodeAs(HttpStatus.FORBIDDEN)) {
            return new TpException(TpErrorCodes.UNAUTHORIZED_ERROR);
        }
        else {
            return new TpException(TpErrorCodes.USER_PROFILE_API_ERROR);
        }
    }

    private Map<String,TpErrorCodes> registerErrorCodeMap() {
        Map<String,TpErrorCodes> errorCodeMap = new HashMap<>();
        errorCodeMap.put("1000", TpErrorCodes.UNAUTHORIZED_ERROR);
        errorCodeMap.put("1001", TpErrorCodes.UNAUTHORIZED_ACCESS_ERROR);
        errorCodeMap.put("1002", TpErrorCodes.INVALID_REQUEST);
        errorCodeMap.put("1003", TpErrorCodes.INVALID_USERNAME_OR_PASSWORD);
        errorCodeMap.put("1099", TpErrorCodes.USER_PROFILE_API_ERROR);
        return errorCodeMap;
    }

}
