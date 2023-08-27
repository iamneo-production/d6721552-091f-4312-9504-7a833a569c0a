package com.project.blogs.services;

import java.util.List;

import com.project.blogs.dao.UserRepo;
import com.project.blogs.exception.ApiException;
import com.project.blogs.exception.ErrorCodes;
import com.project.blogs.model.User;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	List<User> list;

	@Autowired
	public UserRepo userrepo;

	@Override
	public List<User> getAllUsers() {
		return (List<User>) userrepo.findAll();
	}
	// get user by id
	@Override
	public User getUserById(int id) {
		Optional<User> user = userrepo.findById(id);
		if (user.isEmpty()) {
			throw new ApiException(ErrorCodes.USER_NOT_FOUND);
		}
		return user.get();
	}

// Delete user
	@Override
	public User delete(Integer id) {
		Optional<User> user = userrepo.findById(id);
		User foundUser;
		if (user.isPresent()) {
			foundUser = user.get();
			userrepo.delete(foundUser);
		} else {
			throw new ApiException(ErrorCodes.USER_NOT_FOUND);
		}
		return foundUser;
	}
	// save user
	  public boolean saveUser(User user) {
	        for (User obj : userrepo.findAll()) {
	            if (obj.getUsername().equals(user.getUsername())) {
	                throw new ApiException(ErrorCodes.USER_ALREADY_EXISTS);
	            }
	        }
		userrepo.save(user);
		return true;
	  }

	public User findById(int id) {
		return userrepo.findByUserId(id);
	}

}
