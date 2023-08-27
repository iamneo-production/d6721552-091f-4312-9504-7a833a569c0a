package com.project.blogs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.blogs.dao.PostsRepo;
import com.project.blogs.exception.ApiException;
import com.project.blogs.exception.ErrorCodes;
import com.project.blogs.model.Post;

@Service
public class PostsServiceImpl implements PostsService {
	
	@Autowired
	private PostsRepo postrepo;
	// add post
	@Override
	public Post savePost(Post post) {
		return postrepo.save(post);
	}
	// view all post
	@Override
	public List<Post> getAllPosts() {
		return postrepo.findAll();
	}
	// retieve post by id
	@Override
	public Post getPostById(int postId) {
		Optional<Post> i = postrepo.findById(postId);
		Post foundPost;
		if (i.isPresent()) {
			foundPost = i.get();
		} else {
			throw new ApiException(ErrorCodes.POST_NOT_FOUND);
		}
		return foundPost;
	}
	// retieve post by language
	@Override
	public Post getPostByLanguage(String language) {
		Optional<Post> i = postrepo.findByLanguage(language);
		Post foundPost;
		if (i.isPresent()) {
			foundPost = i.get();
		} else {
			throw new ApiException(ErrorCodes.POST_NOT_FOUND);
		}
		return foundPost;
	}
	// Update post
	@Override
	public Post updatePost(Post post, int postId) {
		Optional<Post> i = postrepo.findById(postId);
		Post foundPost;
		if (i.isPresent()) {
			foundPost = i.get();
			foundPost.setPostName(post.getPostName());
			foundPost.setLanguage(post.getLanguage());
			postrepo.save(foundPost);
		} else {
			throw new ApiException(ErrorCodes.POST_NOT_FOUND);
		}
		return foundPost;
	}
	// Delete post
	@Override
	public Post deletePost(int postId) {
		Optional<Post> i = postrepo.findById(postId);
		Post foundPost;
		if (i.isPresent()) {
			foundPost = i.get();
			postrepo.delete(foundPost);
		} else {
			throw new ApiException(ErrorCodes.POST_NOT_FOUND);
		}
		return foundPost;
	}
	// find post by user id
	@Override
	public List<Post> findByUserId(int userId) {
		return postrepo.findByUserUserId(userId);
	}

}
