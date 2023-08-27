package com.project.blogs.services;

import java.util.List;

import com.project.blogs.model.Comment;

public interface CommentService {
	public Comment saveComment(Comment com);

	public List<Comment> getComments();

	public Comment getCommentById(int commentid);

	public String deleteComment(int commentid);

	public List<Comment> findByPostId(int postId);

	public Comment findByCommentIdAndPostId(int postId, int commentid);

	public List<Comment> findByUserId(int userId);
	public Comment updateComment(int commentid, Comment comment);
}
