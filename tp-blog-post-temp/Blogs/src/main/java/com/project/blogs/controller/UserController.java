package com.project.blogs.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.project.blogs.dao.CommentRepo;
import com.project.blogs.dao.PostsRepo;
import com.project.blogs.exception.ApiException;
import com.project.blogs.model.Comment;
import com.project.blogs.model.Post;
import com.project.blogs.model.User;
import com.project.blogs.services.CommentService;
import com.project.blogs.services.PostsService;
import com.project.blogs.services.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private PostsService postsService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private CommentRepo repo;
	@Autowired
	private PostsRepo postrepo;
    
	@PostMapping("/api/tp/user/saveUser")
	public boolean saveUser(@RequestBody User user)
	{
		return userService.saveUser(user);
	}
	// view all users by admin
	@GetMapping("/api/tp/user/users")
	public ResponseEntity<List<User>> getAllUser() {
		List<User> users = userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	// view user by id by bloggers and admin
	@GetMapping("/api/tp/user/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable int userId)  {
	      try {
	            User user = userService.getUserById(userId);
	            return new ResponseEntity<>(user, HttpStatus.OK);
	        } catch (ApiException ex) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	}
	// delete user by bloggers and admin
	@DeleteMapping("/api/tp/user/delete/{userId}")
	public String deleteUser(@PathVariable int userId) {
		List<Comment> comments=commentService.findByUserId(userId);
		List<Post> post=postsService.findByUserId(userId);
		for(Post i:post) {
			repo.deleteInBatch(comments);
			postsService.deletePost(i.getPostId());		
		}
		postrepo.deleteInBatch(post);
		repo.deleteInBatch(comments);
		userService.delete(userId);
		return "deleted";
	}

}
