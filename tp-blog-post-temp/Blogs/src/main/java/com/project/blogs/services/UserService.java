package com.project.blogs.services;

import java.util.List;
import com.project.blogs.model.User;

public interface UserService {
	public List<User> getAllUsers();

	public User getUserById(int id);

	public User delete(Integer id);

	public boolean saveUser(User user);

	public User findById(int userId);

}