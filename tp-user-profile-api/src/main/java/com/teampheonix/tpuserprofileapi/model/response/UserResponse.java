package com.teampheonix.tpuserprofileapi.model.response;

import com.teampheonix.tpuserprofileapi.entity.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserResponse {

	private String userId;
	private String email;
	private String password;
	private List<Role> roles;

}
