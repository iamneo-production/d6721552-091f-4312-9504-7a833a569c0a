package com.teampheonix.tpuserprofileapi.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
public class ApiRequestHeaderFilter extends OncePerRequestFilter implements Ordered {

    private static final String API_KEY = "dHAtdXNlci1wcm9maWxlLWFwaQ==";
    private static final String API_KEY_HEADER = "API_KEY";
    private static final String USER_ID_HEADER = "USER_ID";
    private static final String CLAIMS_HEADER = "CLAIMS";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String apiKey = request.getHeader(API_KEY_HEADER);
        String userID = request.getHeader(USER_ID_HEADER);
        String claims = request.getHeader(CLAIMS_HEADER);

        if (StringUtils.isBlank(apiKey) || !StringUtils.equals(apiKey, API_KEY)
                || StringUtils.isBlank(userID) || StringUtils.isBlank(claims)) {
            log.error("Invalid Request Headers");
//            throw new TpException();
        }
        filterChain.doFilter(request,response);
    }

    @Override
    public int getOrder() {
        return -1;
    }

}
