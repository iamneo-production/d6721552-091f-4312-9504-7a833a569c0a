package com.teampheonix.tpblogpostapi.services;

import com.teampheonix.tpblogpostapi.entity.Comment;

import java.util.List;

public interface CommentService {

	Comment saveComment(long postId, Comment comment);
	String deleteComment(long postId, long commentId);
	List<Comment> findByPostId(long postId);
	Comment updateComment(long commentId, Comment comment);

}
