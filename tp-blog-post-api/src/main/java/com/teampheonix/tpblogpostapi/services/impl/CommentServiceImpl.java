package com.teampheonix.tpblogpostapi.services.impl;

import java.util.List;
import java.util.Optional;

import com.teampheonix.tpblogpostapi.repository.CommentRepository;
import com.teampheonix.tpblogpostapi.exception.ApiException;
import com.teampheonix.tpblogpostapi.entity.Comment;
import com.teampheonix.tpblogpostapi.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository repo;

	@Override
	public Comment saveComment(Comment comment) {
		return null;
	}

	@Override
	public List<Comment> getComments() {
		return null;
	}

	@Override
	public Comment getCommentById(long commentId) {
		return null;
	}

	@Override
	public String deleteComment(long commentId) {
		return null;
	}

	@Override
	public String deleteCommentsByPostId(long postId) {
		return null;
	}

	@Override
	public List<Comment> findByPostId(long postId) {
		return null;
	}

	@Override
	public List<Comment> findByUserId(long userId) {
		return null;
	}

	@Override
	public Comment updateComment(long commentId, Comment comment) {
		return null;
	}

}
