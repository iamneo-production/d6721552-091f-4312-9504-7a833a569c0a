package com.project.blogs.services;

import java.util.List;

import com.project.blogs.model.Post;

public interface PostsService {
	// create
	public Post savePost(Post post);

	// read
	public List<Post> getAllPosts();

	public Post getPostById(int postId);

	// update
	public Post updatePost(Post post, int postId);

	// delete
	public Post deletePost(int imageId);

	public List<Post> findByUserId(int userId);
	public Post getPostByLanguage(String Language);

}
