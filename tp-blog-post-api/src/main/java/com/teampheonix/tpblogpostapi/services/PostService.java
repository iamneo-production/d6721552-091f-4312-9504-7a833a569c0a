package com.teampheonix.tpblogpostapi.services;

import com.teampheonix.tpblogpostapi.entity.Post;
import com.teampheonix.tpblogpostapi.model.request.PostRequest;
import com.teampheonix.tpblogpostapi.model.response.PostResponse;
import com.teampheonix.tpblogpostapi.model.response.PostSummaryResponse;

import java.util.List;


public interface PostService {

	PostResponse createPost(PostRequest postRequest, String userId, String roles);
	PostResponse addLanguageContentToPost(PostRequest postRequest, String userId, String role);
	List<PostResponse> getAllPosts(String userId, String roles);
	List<PostResponse> findPostsByUserId(String userId, String roles);
	PostResponse getPostByLanguage(long postId, String language, String userId, String roles);
	PostResponse getPostById(long postId, String userId, String roles);
	Post updatePost(long postId, String postName);
	String deletePost(long posId, String userId, String roles);
	PostSummaryResponse addPostToTopic(long postId, long topicId);
	String removePostFromTopic(long postId, long topicId);
	String deletePostTopicsById(long topicId);
	List<PostSummaryResponse> getPostsOfTopic(long topicId);

}
