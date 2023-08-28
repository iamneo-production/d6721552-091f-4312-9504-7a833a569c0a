package com.teampheonix.tpuserprofileapi.model.response;

import com.teampheonix.tpuserprofileapi.model.RoleNames;
import lombok.Data;

import java.util.List;

@Data
public class UserClaimsResponse {

	private String userID;
	private List<RoleNames> roles;
	
}
