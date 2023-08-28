package com.teamphoenix.tpauthapi.controller;

import com.teamphoenix.tpauthapi.exception.ApiErrorCodes;
import com.teamphoenix.tpauthapi.exception.ApiException;
import com.teamphoenix.tpauthapi.model.AuthRequest;
import com.teamphoenix.tpauthapi.model.ResponseDto;
import com.teamphoenix.tpauthapi.model.Token;
import com.teamphoenix.tpauthapi.model.UserClaims;
import com.teamphoenix.tpauthapi.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ResponseDto<String>> authenticate(@RequestBody AuthRequest authRequest, HttpServletResponse response) {
        validateAuthRequest(authRequest);
        UserClaims claims = authService.validateUser(authRequest);
        String claimsPayload = claims.getUserID() + ";" + String.join(",", claims.getRoles());
        String authKey = Base64.getEncoder().encodeToString(claimsPayload.getBytes());
        Cookie cookie = new Cookie("AUTH_KEY", authKey);
        cookie.setMaxAge(24 * 60 * 60);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setDomain("localhost");
        response.addCookie(cookie);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.forSuccess("User authenticated successfully"));
    }

    @PostMapping("/token")
    public ResponseEntity<ResponseDto<Token>> generateAccessToken(@RequestBody AuthRequest authRequest) {
        validateAuthRequest(authRequest);
        authService.validateUser(authRequest);
        Token token = authService.generateAccessToken(authRequest.getUserID());
        return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.forSuccess(token));
    }

    @PostMapping("/validate")
    public ResponseEntity<ResponseDto<String>> validateToken(@RequestBody String accessToken) {
        return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.forSuccess(authService.validateAccessToken(accessToken)));
    }

    private void validateAuthRequest(AuthRequest request) {
        if(Objects.isNull(request)
                || StringUtils.isBlank(request.getUserID())
                || StringUtils.isBlank(request.getPassword())) {
            throw new ApiException(ApiErrorCodes.INVALID_REQUEST);
        }
    }

}
