package com.teampheonix.tpuserprofileapi.service;

import com.teampheonix.tpuserprofileapi.model.*;

public interface UserService {

	void registerUser(UserRequest user);

    UserResponse getUser(String userId);

	User updateUser(User user, String userId);

	String deleteUser(String userId);

	UserClaimsResponse login(UserAuthRequest userRequest);

}
