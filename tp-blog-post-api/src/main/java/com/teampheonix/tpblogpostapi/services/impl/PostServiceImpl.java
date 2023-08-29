package com.teampheonix.tpblogpostapi.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.teampheonix.tpblogpostapi.client.LanguageManagementClientApi;
import com.teampheonix.tpblogpostapi.entity.Post;
import com.teampheonix.tpblogpostapi.exception.ApiErrorCodes;
import com.teampheonix.tpblogpostapi.exception.ApiException;
import com.teampheonix.tpblogpostapi.model.request.PostRequest;
import com.teampheonix.tpblogpostapi.model.response.LanguageContentResponse;
import com.teampheonix.tpblogpostapi.model.response.PostResponse;
import com.teampheonix.tpblogpostapi.repository.CommentRepository;
import com.teampheonix.tpblogpostapi.repository.PostRepository;
import com.teampheonix.tpblogpostapi.services.PostService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

	private static final String API_KEY = "dHAtbGFuZ3VhZ2UtbWFuYWdlbWVudC1hcGk=";
	
	@Autowired
	private PostRepository postRepository;

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private LanguageManagementClientApi languageManagementClientApi;

	@Override
	@Transactional
	public PostResponse createPost(PostRequest postRequest, String userId, String roles) {
		Post post = new Post();
		post.setPostName(postRequest.getPostName());
		post.setUserId(userId);
		post = postRepository.save(post);
		postRequest.getContent().setPostId(post.getPostId());
		LanguageContentResponse response =
				languageManagementClientApi.createContent(postRequest.getContent(), API_KEY, userId, roles).getData();
		if (response == null) {
			throw new ApiException(ApiErrorCodes.LANGUAGE_MANAGEMENT_API_ERROR);
		}
		return new PostResponse(post, List.of(response));
	}

	@Override
	@Transactional
	public PostResponse addLanguageContentToPost(PostRequest postRequest, String userId, String roles) {
		Post post = postRepository.findById(postRequest.getContent().getPostId())
				.orElseThrow(() -> new ApiException(ApiErrorCodes.POST_NOT_FOUND));
		LanguageContentResponse response =
				languageManagementClientApi.createContent(postRequest.getContent(), API_KEY, userId, roles).getData();
		if (response == null) {
			throw new ApiException(ApiErrorCodes.LANGUAGE_MANAGEMENT_API_ERROR);
		}
		return new PostResponse(post, List.of(response));
	}

	@Override
	public List<PostResponse> getAllPosts(String userId, String roles) {
		List<Post> posts = postRepository.findAll();
		List<PostResponse> postResponses = new ArrayList<>();
		posts.forEach(p -> {
			List<LanguageContentResponse> responses =
					languageManagementClientApi.getContentsByPostId(p.getPostId(), API_KEY, userId, roles).getData();
			postResponses.add(new PostResponse(p, responses));
		});
		return postResponses;
	}

	@Override
	public PostResponse getPostById(long postId, String userId, String roles) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ApiException(ApiErrorCodes.POST_NOT_FOUND));
		List<LanguageContentResponse> responses =
				languageManagementClientApi.getContentsByPostId(postId, API_KEY, userId, roles).getData();
		return new PostResponse(post, responses);
	}

	@Override
	public Post findPostById(long postId) {
		return postRepository.findById(postId).orElseThrow(() -> new ApiException(ApiErrorCodes.POST_NOT_FOUND));
	}

	@Override
	public Post updatePost(long postId, String postName) {
		Post post = postRepository.findById(postId).orElseThrow(() -> new ApiException(ApiErrorCodes.POST_NOT_FOUND));
		post.setPostName(postName);
		return postRepository.save(post);
	}

	@Override
	@Transactional
	public String deletePost(long postId, String userId, String roles) {
		languageManagementClientApi.deleteContentsByPostId(postId, API_KEY, userId, roles);
		postRepository.deleteById(postId);
		return "Deleted Successfully";
	}

	@Override
	public List<PostResponse> findPostsByUserId(String userId, String roles) {
		List<Post> posts = postRepository.findPostsByUserId(userId);
		List<PostResponse> postResponses = new ArrayList<>();
		posts.forEach(p -> {
			List<LanguageContentResponse> responses =
					languageManagementClientApi.getContentsByPostId(p.getPostId(), API_KEY, userId, roles).getData();
			postResponses.add(new PostResponse(p, responses));
		});
		return postResponses;
	}

	@Override
	public PostResponse getPostByLanguage(long postId, String language, String userId, String roles) {
		Post post = postRepository.findById(postId).orElseThrow(() -> new ApiException(ApiErrorCodes.POST_NOT_FOUND));
		LanguageContentResponse response =
				languageManagementClientApi.getContentByLanguage(postId, language, API_KEY, userId, roles).getData();
		return new PostResponse(post, List.of(response));
	}

}
