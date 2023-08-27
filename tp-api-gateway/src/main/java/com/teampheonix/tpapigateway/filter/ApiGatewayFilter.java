package com.teampheonix.tpapigateway.filter;

import com.teampheonix.tpapigateway.exception.TpException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Component
@Slf4j
public class ApiGatewayFilter implements GlobalFilter, Ordered {

    private static final String AUTH_KEY = "AUTH_KEY";
    private static final String USER_ID = "USER_ID";
    private static final String CLAIMS = "CLAIMS";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpCookie authKey = exchange.getRequest().getCookies().getFirst(AUTH_KEY);
        if (authKey == null || StringUtils.isBlank(authKey.getValue())) {
            log.error("Unauthorized User - auth key is not present");
            throw new TpException(HttpStatus.UNAUTHORIZED, "Unauthorized User");
        }
        List<String> userDetails = Arrays.asList(new String(Base64.getDecoder().decode(authKey.getValue())).split(";"));
        if (userDetails.size() <= 1) {
            log.error("Unauthorized User - Invalid Auth Key");
            throw new TpException(HttpStatus.UNAUTHORIZED, "Unauthorized User - Invalid Auth Key");
        }
        exchange.getRequest()
                .mutate()
                .headers(httpHeaders -> {
                    httpHeaders.set(USER_ID, userDetails.get(0));
                    httpHeaders.set(CLAIMS, userDetails.get(1));
                });
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }

}
