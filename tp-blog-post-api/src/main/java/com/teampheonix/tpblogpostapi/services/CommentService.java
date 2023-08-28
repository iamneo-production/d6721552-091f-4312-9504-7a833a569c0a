package com.teampheonix.tpblogpostapi.services;

import com.teampheonix.tpblogpostapi.entity.Comment;

import java.util.List;

public interface CommentService {

	Comment saveComment(Comment comment);
	List<Comment> getComments();
	Comment getCommentById(long commentId);
	String deleteComment(long commentId);
	String deleteCommentsByPostId(long postId);
	List<Comment> findByPostId(long postId);
	List<Comment> findByUserId(long userId);
	Comment updateComment(long commentId, Comment comment);

}
