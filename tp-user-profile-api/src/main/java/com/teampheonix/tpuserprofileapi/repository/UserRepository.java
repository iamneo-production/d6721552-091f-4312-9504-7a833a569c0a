package com.teampheonix.tpuserprofileapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;



import com.teampheonix.tpuserprofileapi.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	 User findByUserId(String userId);

	void deleteByUserId(String userId);

	User findUserByUserIdAndPassword(String userId, String password);

}
