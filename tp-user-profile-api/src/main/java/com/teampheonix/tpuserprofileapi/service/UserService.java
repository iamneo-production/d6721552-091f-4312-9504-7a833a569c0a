package com.teampheonix.tpuserprofileapi.service;

import com.teampheonix.tpuserprofileapi.entity.User;
import com.teampheonix.tpuserprofileapi.model.request.UserAuthRequest;
import com.teampheonix.tpuserprofileapi.model.request.UserRequest;
import com.teampheonix.tpuserprofileapi.model.response.UserClaimsResponse;
import com.teampheonix.tpuserprofileapi.model.response.UserResponse;

public interface UserService {

	void registerUser(UserRequest user);

    UserResponse getUser(String userId);

	User updateUser(User user, String userId);

	String deleteUser(String userId);

	UserClaimsResponse login(UserAuthRequest userRequest);

}
