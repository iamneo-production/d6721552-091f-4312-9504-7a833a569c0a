package com.teampheonix.tpapigateway.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestControllerAdvice
@Slf4j
public class ApiGatewayExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TpException.class)
    public Mono<ServerResponse> handleTpException(ServerWebExchange exchange, TpException exception) {
        exchange.getAttributes().putIfAbsent(ErrorAttributes.ERROR_ATTRIBUTE, exception);
        return ServerResponse.from(
            ErrorResponse.builder(exception,HttpStatus.UNAUTHORIZED,exception.getMessage()).build());
    }

}
