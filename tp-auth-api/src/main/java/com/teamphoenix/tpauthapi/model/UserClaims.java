package com.teamphoenix.tpauthapi.model;

import lombok.Data;

import java.util.List;

@Data
public class UserClaims {

    private List<String> userID;
    private List<String> roles;

}
