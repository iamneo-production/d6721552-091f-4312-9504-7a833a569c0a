package com.project.blogs.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.project.blogs.dao.CommentRepo;
import com.project.blogs.exception.ApiException;
import com.project.blogs.exception.ErrorCodes;
import com.project.blogs.model.Comment;
import com.project.blogs.model.Post;
import com.project.blogs.model.User;
import com.project.blogs.services.CommentServiceImpl;
import com.project.blogs.services.PostsService;

import com.project.blogs.services.UserServiceImpl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tp/blog-post")
public class PostsController {
	@Autowired
	private PostsService postsService;
	@Autowired
	private CommentRepo repo;
	@Autowired
	private UserServiceImpl userService;
	@Autowired
	private CommentServiceImpl commentService;

	@GetMapping("/posts/language/{language}")
    public ResponseEntity<Post> getBlogPostByLanguage(
        @RequestParam(name = "language", defaultValue = "en") String language
    ) {
        Post blogPost = postsService.getPostByLanguage(language);
        if (blogPost != null) {
            return ResponseEntity.ok(blogPost);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

	// View Posts
	@GetMapping("/posts")
	public ResponseEntity<List<Post>> getAllImagesAdmin() {
		List<Post> images = postsService.getAllPosts();
		return new ResponseEntity<>(images, HttpStatus.OK);
	}

	// Get Post By Id 
	@GetMapping("/posts/{postId}")
	public ResponseEntity<Post> getImageById(@PathVariable int postId) {
		Post posts = postsService.getPostById(postId);
		return new ResponseEntity<>(posts, HttpStatus.OK);
	}

	// Add Posts
	@PostMapping("/posts/add/{userId}")
	public ResponseEntity<Post> addPosts(@RequestBody Post posts, @PathVariable("userId") int userId) {
		User user = userService.findById(userId);
		posts.setUser(userService.getUserById(user.getUserId()));
		Post addPost = postsService.savePost(posts);
		return new ResponseEntity<>(addPost, HttpStatus.CREATED);
	}

	// Delete Posts
	@DeleteMapping("/posts/delete/{postId}")
	public String deletePosts(@PathVariable int postId) {
	      try {
	    	  List<Comment> comments = commentService.findByPostId(postId);
	  		if (comments.isEmpty()) {
	  			postsService.deletePost(postId);
	  		}
	  		repo.deleteInBatch(comments);
	  		postsService.deletePost(postId);
	  		return "deleted";
	        } catch (ApiException ex) {
	        	throw new ApiException(ErrorCodes.POST_NOT_FOUND);
	        }
		
	}

// Update Posts
	@PutMapping("/posts/update/{postId}")
	public ResponseEntity<Post> updatePosts(@RequestBody Post posts, @PathVariable int postId) {
		Post updatePost = postsService.updatePost(posts, postId);
		return new ResponseEntity<>(updatePost, HttpStatus.OK);
	}
// Get Posts By Language

}
