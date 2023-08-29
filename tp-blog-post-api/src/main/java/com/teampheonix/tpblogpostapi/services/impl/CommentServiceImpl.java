package com.teampheonix.tpblogpostapi.services.impl;

import java.util.List;

import com.teampheonix.tpblogpostapi.entity.Post;
import com.teampheonix.tpblogpostapi.exception.ApiErrorCodes;
import com.teampheonix.tpblogpostapi.repository.CommentRepository;
import com.teampheonix.tpblogpostapi.exception.ApiException;
import com.teampheonix.tpblogpostapi.entity.Comment;
import com.teampheonix.tpblogpostapi.repository.PostRepository;
import com.teampheonix.tpblogpostapi.services.CommentService;
import com.teampheonix.tpblogpostapi.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private PostService postService;

	@Override
	public Comment saveComment(long postId, Comment commentRequest) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ApiException(ApiErrorCodes.POST_NOT_FOUND));
		Comment comment = commentRepository.save(commentRequest);
		post.getComments().add(comment);
		postRepository.save(post);
		return comment;
	}

	@Override
	public String deleteComment(long postId, long commentId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ApiException(ApiErrorCodes.POST_NOT_FOUND));
		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new ApiException(ApiErrorCodes.INVALID_REQUEST));
		post.getComments().remove(comment);
		postRepository.save(post);
		commentRepository.delete(comment);
		return "Deleted Successfully";
	}

	@Override
	public List<Comment> findByPostId(long postId) {
		return postRepository.findById(postId)
				.orElseThrow(() -> new ApiException(ApiErrorCodes.POST_NOT_FOUND)).getComments();
	}

	@Override
	public Comment updateComment(long commentId, Comment comment) {
		Comment entity = commentRepository.findById(commentId).orElseThrow(() -> new ApiException(ApiErrorCodes.INVALID_REQUEST));
		entity.setComment(comment.getComment());
		return commentRepository.save(entity);
	}

}
