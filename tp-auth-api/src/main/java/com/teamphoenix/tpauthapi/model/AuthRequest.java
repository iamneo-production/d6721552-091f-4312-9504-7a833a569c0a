package com.teamphoenix.tpauthapi.model;

import lombok.Data;

@Data
public class AuthRequest {

    private String userID;
    private String password;

}
