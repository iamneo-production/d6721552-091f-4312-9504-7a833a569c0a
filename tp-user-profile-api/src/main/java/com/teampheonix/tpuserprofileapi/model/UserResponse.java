package com.teampheonix.tpuserprofileapi.model;

import lombok.Data;

import java.util.List;

@Data
public class UserResponse {

	private String userId;
	private String email;
	private String password;
	private List<Role> roles;

}
