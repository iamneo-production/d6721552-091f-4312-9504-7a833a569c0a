package com.teampheonix.tpblogpostapi.services.impl;

import java.util.List;

import com.teampheonix.tpblogpostapi.entity.Post;
import com.teampheonix.tpblogpostapi.exception.ApiErrorCodes;
import com.teampheonix.tpblogpostapi.exception.ApiException;
import com.teampheonix.tpblogpostapi.repository.PostRepository;
import com.teampheonix.tpblogpostapi.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public Post savePost(Post post) {
		return null;
	}

	@Override
	public List<Post> getAllPosts() {
		return null;
	}

	@Override
	public Post getPostById(long postId) {
		return null;
	}

	@Override
	public Post updatePost(Post post) {
		return null;
	}

	@Override
	public Post deletePost(long posId) {
		return null;
	}

	@Override
	public List<Post> findPostsByUserId(String userId) {
		return null;
	}

	@Override
	public Post getPostByLanguage(String Language) {
		return null;
	}

}
