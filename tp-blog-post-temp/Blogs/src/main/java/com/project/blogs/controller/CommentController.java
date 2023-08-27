package com.project.blogs.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.blogs.model.Comment;
import com.project.blogs.services.CommentServiceImpl;
import com.project.blogs.services.PostsService;
import com.project.blogs.services.UserService;

@RestController
@RequestMapping("/api/tp/blog-post/")
public class CommentController {
	@Autowired
	private UserService userService;
	@Autowired
	private CommentServiceImpl commentService;
	@Autowired
	private PostsService postService;
	/*
	/api/tp/blog-post/posts/{post-id}/comments GET - 
	/api/tp/blog-post/posts/{post-id}/comment POST - 
	/api/tp/blog-post/posts/{post-id}/comments/{comment-id} PUT - 
	/api/tp/blog-post/posts/{post-id}/comments/{comment-id} DELETE 
*/
	// adding comment on a perticular post by a perticular user
	@PostMapping(value = "/posts/{postId}/addComment/{userId}")
	public String addCommentByPostId(@PathVariable(value = "postId") int postId, @RequestBody Comment comment,
			@PathVariable(value = "userId") int userId) {
		comment.setUser(userService.findById(userId));
		comment.setPost(postService.getPostById(postId));
		commentService.saveComment(comment);
		return "added";
	}
	/*
    // Viewing comment by id
	@GetMapping("/{commentid}")
	public Comment getCommentById(@PathVariable(value = "commentid") int commentid) {
		return commentService.getCommentById(commentid);
	}

//viewing all comments
	@GetMapping("/allcomments")
	public List<Comment> allComments() {
		return commentService.getComments();
	}
	*/
	/*
	@PostMapping("/post/addComment")
	public Comment saveComment(@RequestBody Comment comment) {
		Comment addComment = commentService.saveComment(comment);
		return addComment;
	}
*/
// Viewing a perticular posts comments
	@GetMapping("/post/{postId}/comments")
	public ResponseEntity<List<Comment>> getAllCommentsByPostId(@PathVariable(value = "postId") int imageId) {
		List<Comment> comment = commentService.findByPostId(imageId);
		return new ResponseEntity<>(comment, HttpStatus.OK);
	}

	//uptade comments
	@PutMapping("/post/{postId}/update/{commentid}")
	public ResponseEntity<Comment> updateComment(@RequestBody Comment comment, @PathVariable int commentid) {
		Comment c = commentService.updateComment(commentid, comment);
		return new ResponseEntity<>(c, HttpStatus.CREATED);
	}
	// delete comments
	@DeleteMapping("/post/{postId}/delete/{commentid}")
	public ResponseEntity<String> deleteComment(@PathVariable int commentid) {
		String deleteComment = commentService.deleteComment(commentid);
		return new ResponseEntity<>(deleteComment, HttpStatus.OK);
	}

}
