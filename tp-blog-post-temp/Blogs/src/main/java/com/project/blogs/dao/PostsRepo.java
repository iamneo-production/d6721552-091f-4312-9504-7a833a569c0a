package com.project.blogs.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.blogs.model.Comment;
import com.project.blogs.model.Post;
@Repository
public interface PostsRepo extends JpaRepository<Post, Integer> {
	List<Comment> findByPostId(int id);
	public List<Post> findByUserUserId(int userId);
	public Optional<Post> findByLanguage(String Language);
}
