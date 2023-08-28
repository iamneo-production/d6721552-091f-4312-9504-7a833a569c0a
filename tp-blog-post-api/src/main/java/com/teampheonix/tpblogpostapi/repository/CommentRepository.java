package com.teampheonix.tpblogpostapi.repository;

import java.util.List;

import com.teampheonix.tpblogpostapi.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

	 List<Comment> findByPostPostId(int PostId);
	 Comment findByPostIdAndCommentId(int PostId);
	 List<Comment> findByUserUserId(int userId);

}
