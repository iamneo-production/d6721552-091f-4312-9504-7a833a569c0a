package com.project.blogs.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.blogs.model.User;

@Repository
public interface UserRepo extends CrudRepository<User, Integer> {

	User findByUserId(int userId);

}
