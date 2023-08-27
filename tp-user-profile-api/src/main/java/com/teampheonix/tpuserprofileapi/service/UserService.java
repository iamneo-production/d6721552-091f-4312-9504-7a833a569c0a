package com.teampheonix.tpuserprofileapi.service;

import com.teampheonix.tpuserprofileapi.model.User;
import com.teampheonix.tpuserprofileapi.model.UserResponse;


public interface UserService {

	void registerUser(User user);

    UserResponse getUser(String userId);

	User updateUser(User user, String userId);


	String deleteUser(String userId);


	User login(User userRequest);
}
