package com.teampheonix.tpuserprofileapi.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.teampheonix.tpuserprofileapi.entity.Role;
import com.teampheonix.tpuserprofileapi.entity.User;
import com.teampheonix.tpuserprofileapi.exception.ApiErrorCodes;
import com.teampheonix.tpuserprofileapi.exception.ApiException;
import com.teampheonix.tpuserprofileapi.model.request.UserAuthRequest;
import com.teampheonix.tpuserprofileapi.model.request.UserRequest;
import com.teampheonix.tpuserprofileapi.model.response.UserClaimsResponse;
import com.teampheonix.tpuserprofileapi.model.response.UserResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teampheonix.tpuserprofileapi.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repo;

	public void registerUser(UserRequest user) {
		validateUser(user);
		User existingUser = repo.findByUserId(user.getUserId());
		if (existingUser != null) {
			log.error("UserId is already Exist");
			throw new ApiException(ApiErrorCodes.USER_ALREADY_EXISTS);
		}

		User userEntity = new User();
		userEntity.setUserId(user.getUserId());
		userEntity.setEmail(user.getEmail());
		userEntity.setPassword(user.getPassword());
		Set<Role> roleSet = new HashSet<>();
		user.getRole().forEach(r -> roleSet.add(new Role(0, r)));
        userEntity.setRole(roleSet);

		repo.save(userEntity);
		log.info("User registered successfully.");
	}

	public UserResponse getUser(String userId) {
		User user = validateAndGetUser(userId);
		UserResponse userResponse = new UserResponse();
		userResponse.setUserId(user.getUserId());
		userResponse.setEmail(user.getEmail());
		userResponse.setRoles(user.getRole().stream().toList());
		return userResponse;
	}

	public User updateUser(User user, String userId) {
		User existingUser = validateAndGetUser(userId);

		existingUser.setEmail(user.getEmail());
		existingUser.setPassword(user.getPassword());
		existingUser.setRole(user.getRole());
		repo.save(existingUser);
		log.info("User details updated successfully.");
		return existingUser;
	}

	public String deleteUser(String userId) {
		Optional<User> findByUserId = Optional.of(repo.findByUserId(userId));
		if (findByUserId.isEmpty()) {
			log.error("User does not Exist");
			throw new ApiException(ApiErrorCodes.INVALID_USER_ID);

		}
		repo.delete(findByUserId.get());
		log.info("User Deleted successfully.");
		return "Deleted successfully";
	}

	public UserClaimsResponse login(UserAuthRequest userRequest) {
		if(userRequest == null || StringUtils.isBlank(userRequest.getUserID()) ||
			StringUtils.isBlank(userRequest.getPassword())) {
				log.error("Invalid User Request");
				throw new ApiException(ApiErrorCodes.INVALID_REQUEST);
			}
		User user = repo.findUserByUserIdAndPassword(userRequest.getUserID(), userRequest.getPassword());
		if (user == null) {
			log.error("Invalid User Id or Password");
			throw new ApiException(ApiErrorCodes.INVALID_USERNAME_OR_PASSWORD);
		}
		UserClaimsResponse claimsResponse = new UserClaimsResponse();
		claimsResponse.setUserID(user.getUserId());
		claimsResponse.setRoles(new ArrayList<>());
		user.getRole().forEach(r -> claimsResponse.getRoles().add(r.getRoleName()));
		return claimsResponse;
	}

	private void validateUser(UserRequest user) {
		if (user == null || StringUtils.isBlank(user.getUserId()) || StringUtils.isBlank(user.getEmail())
				|| StringUtils.isBlank(user.getPassword())) {
			log.error("Invalid User Request");
			throw new ApiException(ApiErrorCodes.INVALID_REQUEST);
		}
	}

	private User validateAndGetUser(String userId) {
		if (StringUtils.isBlank(userId)) {
			log.error("Invalid User Id");
			throw new ApiException(ApiErrorCodes.INVALID_REQUEST);
		}
		User user = repo.findByUserId(userId);
		if (user == null) {
			log.error("No User Found for User Id : " + userId);
			throw new ApiException(ApiErrorCodes.INVALID_USER_ID);
		}
		return user;
	}

}
