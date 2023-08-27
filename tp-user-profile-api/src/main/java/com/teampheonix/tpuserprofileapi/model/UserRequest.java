package com.teampheonix.tpuserprofileapi.model;

import java.util.Set;
import lombok.Data;

@Data
public class UserRequest {

	private String userId;
	private String email;
	private String password;
	private Set<RoleNames> role;

}
