package com.project.blogs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.blogs.dao.CommentRepo;
import com.project.blogs.exception.ApiException;
import com.project.blogs.exception.ErrorCodes;
import com.project.blogs.model.Comment;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentRepo repo;
	// add comment
	public Comment saveComment(Comment com) {
		return repo.save(com);
	}
	// view comment
	public List<Comment> getComments() {
		return repo.findAll();
	}
	// delete comment
	public String deleteComment(int commentid) {
		repo.deleteById(commentid);
		return "deleted";
	}
	// update comment
	public Comment updateComment(int commentid, Comment comment) {
		Optional<Comment> c = repo.findById(commentid);
		Comment foundComment;
		if (c.isPresent()) {
			foundComment = c.get();
			foundComment.setPost(comment.getPost());
			foundComment.setUser(comment.getUser());
			foundComment.setUserComment(comment.getUserComment());
			repo.save(foundComment);
		} else {
			throw new ApiException(ErrorCodes.COMMENT_NOT_FOUND);
		}
		return foundComment;
	}
	// find comments by PostId
	public List<Comment> findByPostId(int postId) {
		List<Comment> c;
		c = repo.findByPostPostId(postId);
		return c;
	}

	public Comment findByCommentIdAndPostId(int postId, int commentid) {
		return repo.findByPostPostIdAndCommentid(commentid, postId);
	}
	public List<Comment> findByUserId(int userId) {
		return repo.findByUserUserId(userId);
	}

	public Comment getCommentById(int commentid) {
		Optional<Comment> c = repo.findById(commentid);
		Comment foundComment;
		if (c.isPresent()) {
			foundComment = c.get();
		} else {
			throw new ApiException(ErrorCodes.COMMENT_NOT_FOUND);
		}
		return foundComment;
	}

}
