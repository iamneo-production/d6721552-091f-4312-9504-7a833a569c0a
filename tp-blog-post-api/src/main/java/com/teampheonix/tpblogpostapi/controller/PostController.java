package com.teampheonix.tpblogpostapi.controller;

import com.teampheonix.tpblogpostapi.entity.Comment;
import com.teampheonix.tpblogpostapi.entity.Post;
import com.teampheonix.tpblogpostapi.services.CommentService;
import com.teampheonix.tpblogpostapi.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
public class PostController {

	@Autowired
	private PostService postService;

	@GetMapping("/posts/language/{language}")
    public ResponseEntity<Post> getBlogPostByLanguage(
        @RequestParam(name = "language", defaultValue = "en") String language
    ) {
        Post blogPost = postService.getPostByLanguage(language);
        if (blogPost != null) {
            return ResponseEntity.ok(blogPost);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

	// View Posts
	@GetMapping("/posts")
	public ResponseEntity<List<Post>> getAllImagesAdmin() {
		List<Post> images = postService.getAllPosts();
		return new ResponseEntity<>(images, HttpStatus.OK);
	}

	// Get Post By Id 
	@GetMapping("/posts/{postId}")
	public ResponseEntity<Post> getImageById(@PathVariable int postId) {
		Post posts = postService.getPostById(postId);
		return new ResponseEntity<>(posts, HttpStatus.OK);
	}

	// Add Posts
	@PostMapping("/posts/add/{userId}")
	public ResponseEntity<Post> addPosts(@RequestBody Post posts) {
		Post addPost = postService.savePost(posts);
		return new ResponseEntity<>(addPost, HttpStatus.CREATED);
	}

	// Delete Posts
	@DeleteMapping("/posts/delete/{postId}")
	public String deletePosts(@PathVariable long postId) {
		postService.findPostsByUserId("");
		postService.deletePost(postId);
		return "deleted";
	}

// Update Posts
	@PutMapping("/posts/update/{postId}")
	public ResponseEntity<Post> updatePosts(@RequestBody Post posts, @PathVariable long postId) {
		Post updatePost = postService.updatePost(posts);
		return new ResponseEntity<>(updatePost, HttpStatus.OK);
	}
// Get Posts By Language

}
