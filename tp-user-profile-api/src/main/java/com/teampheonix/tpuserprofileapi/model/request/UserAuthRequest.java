package com.teampheonix.tpuserprofileapi.model.request;

import lombok.Data;

@Data
public class UserAuthRequest {

	private String userID;
	private String password;

}
