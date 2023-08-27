package com.teamphoenix.tpauthapi.service;

import com.teamphoenix.tpauthapi.model.AuthRequest;
import com.teamphoenix.tpauthapi.model.Token;
import com.teamphoenix.tpauthapi.model.UserClaims;

public interface AuthService {

    Token generateAccessToken(String userId);
    UserClaims validateUser(AuthRequest request);
    String validateAccessToken(String token);

}
