package com.teampheonix.tpuserprofileapi.model;

import lombok.Data;

import java.util.List;

@Data
public class UserClaimsResponse {

	private String userID;
	private List<RoleNames> roles;
	
}
