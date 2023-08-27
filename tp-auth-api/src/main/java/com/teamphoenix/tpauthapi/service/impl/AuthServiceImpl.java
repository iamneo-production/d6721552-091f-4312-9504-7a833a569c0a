package com.teamphoenix.tpauthapi.service.impl;

import com.teamphoenix.tpauthapi.client.UserProfileClientApi;
import com.teamphoenix.tpauthapi.exception.TpErrorCodes;
import com.teamphoenix.tpauthapi.exception.TpException;
import com.teamphoenix.tpauthapi.model.AuthRequest;
import com.teamphoenix.tpauthapi.model.ResponseDto;
import com.teamphoenix.tpauthapi.model.Token;
import com.teamphoenix.tpauthapi.model.UserClaims;
import com.teamphoenix.tpauthapi.service.AuthService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserProfileClientApi userProfileClientApi;

    String secret = "asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf4";

    public Token generateAccessToken(String userID) {
        Key hmacKey = getKey();

        String jwtToken = Jwts.builder()
                .claim("userID", userID)
                .setSubject(userID)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(15L, ChronoUnit.MINUTES)))
                .signWith(hmacKey)
                .compact();

        if (jwtToken == null) {
            throw new TpException(TpErrorCodes.INVALID_TOKEN);
        }

        Token token = new Token();
        token.setAccessToken(jwtToken);
        return token;
    }

    public String validateAccessToken(String token) {
        Key hmacKey = getKey();

        Jws<Claims> jwt = Jwts.parserBuilder()
                .setSigningKey(hmacKey)
                .build()
                .parseClaimsJws(token);

        if(jwt == null || jwt.getBody() == null || jwt.getBody().get("userID") == null) {
            throw new TpException(TpErrorCodes.INVALID_TOKEN);
        }
        return jwt.getBody().get("userID").toString();
    }

    public UserClaims validateUser(AuthRequest request) {
        ResponseDto<UserClaims> response = userProfileClientApi.validateUser(request, request.getUserID());

        if (response == null || response.getData() == null) {
            throw new TpException(TpErrorCodes.USER_PROFILE_API_ERROR);
        }
        return response.getData();
    }
    
    private Key getKey() {
        return new SecretKeySpec(Base64.getDecoder().decode(secret),
                SignatureAlgorithm.HS256.getJcaName());
    }

}
