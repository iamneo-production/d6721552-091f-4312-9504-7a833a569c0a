package com.teampheonix.tpblogpostapi.repository;

import java.util.List;

import com.teampheonix.tpblogpostapi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

	List<Post> findByUserUserId(int userId);
	Post findByLanguage(String Language);

}
