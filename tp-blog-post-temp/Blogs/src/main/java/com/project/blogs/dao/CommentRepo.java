package com.project.blogs.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.blogs.model.Comment;
@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {
	 public List<Comment> findByPostPostId(int PostId);
	 public Comment findByPostPostIdAndCommentid(int commentid,int PostId);
	 public List<Comment> findByUserUserId(int userId);

}
