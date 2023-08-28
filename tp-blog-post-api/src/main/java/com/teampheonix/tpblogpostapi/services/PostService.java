package com.teampheonix.tpblogpostapi.services;

import com.teampheonix.tpblogpostapi.entity.Post;

import java.util.List;


public interface PostService {

	Post savePost(Post post);
	List<Post> getAllPosts();
	Post getPostById(long postId);
	Post updatePost(Post post);
	Post deletePost(long posId);
	List<Post> findPostsByUserId(String userId);
	Post getPostByLanguage(String Language);

}
