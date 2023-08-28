package com.teampheonix.tpuserprofileapi.model.request;

import java.util.Set;

import com.teampheonix.tpuserprofileapi.model.RoleNames;
import lombok.Data;

@Data
public class UserRequest {

	private String userId;
	private String email;
	private String password;
	private Set<RoleNames> role;

}
