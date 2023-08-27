package com.teampheonix.tpuserprofileapi.service;

import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teampheonix.tpuserprofileapi.exception.InvalidRequestException;
import com.teampheonix.tpuserprofileapi.model.RoleNames;
import com.teampheonix.tpuserprofileapi.model.User;
import com.teampheonix.tpuserprofileapi.model.UserRequest;
import com.teampheonix.tpuserprofileapi.model.UserResponse;
import com.teampheonix.tpuserprofileapi.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repo;

	public void registerUser(User user) {
		validateUser(user);
		User existingUser = repo.findByUserId(user.getUserId());
		if (existingUser != null) {
			log.error("UserId is already Exist");
			throw new InvalidRequestException("UserId is already Exist");
		}

		User userEntity = new User();
		userEntity.setUserId(user.getUserId());
		userEntity.setEmail(user.getEmail());
		userEntity.setPassword(user.getPassword());
        userEntity.setRole(user.getRole());


		repo.save(userEntity);
		log.info("User registered successfully.");
	}

	public UserResponse getUser(String userId) {
		User user = validateAndGetUser(userId);
		UserResponse userResponse = new UserResponse();
		userResponse.setUserId(user.getUserId());
		userResponse.setEmail(user.getEmail());
		userResponse.setPassword(user.getPassword());
		userResponse.setRole(user.getRole());
		log.info("User updated successfully.");
		return userResponse;
	}

	private void validateUser(User user) {
		if (user == null || StringUtils.isBlank(user.getUserId()) || StringUtils.isBlank(user.getEmail())
				|| StringUtils.isBlank(user.getPassword())) {
			log.error("Invalid User Request");
			throw new InvalidRequestException("Invalid User Request");
		}
	}

	private User validateAndGetUser(String userId) {
		if (StringUtils.isBlank(userId)) {
			log.error("Invalid User Id");
			throw new InvalidRequestException("Invalid User Id");
		}
		User user = repo.findByUserId(userId);
		if (user == null) {
			log.error("No User Found for User Id : " + userId);
			throw new InvalidRequestException("No User Found for User Id : " + userId);
		}
		return user;
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
			throw new InvalidRequestException("User does not Exist");

		}
		repo.delete(findByUserId.get());
		log.info("User Deleted successfully.");
		return "Deleted successfully";
	}

	    public User login(User userRequest) {
	    	
	    	if(userRequest == null || StringUtils.isBlank(userRequest.getUserId()) || 
		            StringUtils.isBlank(userRequest.getPassword())) {
		        		log.error("Invalid User Request");
		                throw new InvalidRequestException("Invalid User Request");
		            } 
	        return repo.findUserByUserIdAndPassword(userRequest.getUserId(), userRequest.getPassword());
	    }

	
	

}
