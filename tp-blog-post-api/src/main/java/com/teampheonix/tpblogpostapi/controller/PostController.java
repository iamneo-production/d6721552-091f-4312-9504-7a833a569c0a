package com.teampheonix.tpblogpostapi.controller;

import com.teampheonix.tpblogpostapi.aspect.AuthorizeRoles;
import com.teampheonix.tpblogpostapi.aspect.RolesConstants;
import com.teampheonix.tpblogpostapi.entity.Post;
import com.teampheonix.tpblogpostapi.exception.ApiErrorCodes;
import com.teampheonix.tpblogpostapi.exception.ApiException;
import com.teampheonix.tpblogpostapi.model.ResponseDto;
import com.teampheonix.tpblogpostapi.model.request.PostRequest;
import com.teampheonix.tpblogpostapi.model.response.PostResponse;
import com.teampheonix.tpblogpostapi.model.response.PostSummaryResponse;
import com.teampheonix.tpblogpostapi.services.PostService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/blog-post")
public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping("/post")
	@AuthorizeRoles(roles = { RolesConstants.ROLES_BLOGGER })
	public ResponseEntity<ResponseDto<PostResponse>> createPost(HttpServletRequest request,
														@RequestBody PostRequest postRequest) {
		String userId = request.getHeader("USER_ID");
		String roles = request.getHeader("CLAIMS");
		PostResponse post = postService.createPost(postRequest, userId, roles);
		return ResponseEntity.ok(ResponseDto.forSuccess(post));
	}

	@PutMapping("/post/{postId}/content")
	@AuthorizeRoles(roles = { RolesConstants.ROLES_BLOGGER, RolesConstants.ROLES_CONTENT_MODERATOR,
			RolesConstants.ROLES_TRANSLATOR, RolesConstants.ROLES_ADMIN })
	public ResponseEntity<ResponseDto<PostResponse>> addContentToPost(HttpServletRequest request,
																	  @RequestBody PostRequest postRequest,
																	  @PathVariable("postId") long postId) {
		if (postRequest.getContent().getPostId() != postId) {
			throw new ApiException(ApiErrorCodes.INVALID_REQUEST);
		}
		String userId = request.getHeader("USER_ID");
		String roles = request.getHeader("CLAIMS");
		PostResponse post = postService.addLanguageContentToPost(postRequest, userId, roles);
		return ResponseEntity.ok(ResponseDto.forSuccess(post));
	}

	@GetMapping("/posts/{postId}")
    public ResponseEntity<ResponseDto<PostResponse>> getPostByLanguage(HttpServletRequest request,
			@PathVariable("postId") long postId,
			@RequestParam(name = "language", defaultValue = "en") String language) {
		String userId = request.getHeader("USER_ID");
		String roles = request.getHeader("CLAIMS");
        PostResponse postResponse = postService.getPostByLanguage(postId, language, userId, roles);
		return ResponseEntity.ok(ResponseDto.forSuccess(postResponse));
    }

	@GetMapping("/posts/all")
	public ResponseEntity<ResponseDto<List<PostResponse>>> getAllPosts(HttpServletRequest request) {
		String userId = request.getHeader("USER_ID");
		String roles = request.getHeader("CLAIMS");
		List<PostResponse> posts = postService.getAllPosts(userId, roles);
		return ResponseEntity.ok(ResponseDto.forSuccess(posts));
	}

	@GetMapping("/posts")
	public ResponseEntity<ResponseDto<List<PostResponse>>> getPostsOfUser(HttpServletRequest request) {
		String userId = request.getHeader("USER_ID");
		String roles = request.getHeader("CLAIMS");
		List<PostResponse> posts = postService.findPostsByUserId(userId, roles);
		return ResponseEntity.ok(ResponseDto.forSuccess(posts));
	}

	@GetMapping("/post/{postId}")
	public ResponseEntity<ResponseDto<PostResponse>> getPostByPostId(@PathVariable long postId,
																	 HttpServletRequest request) {
		String userId = request.getHeader("USER_ID");
		String roles = request.getHeader("CLAIMS");
		PostResponse posts = postService.getPostById(postId, userId, roles);
		return ResponseEntity.ok(ResponseDto.forSuccess(posts));
	}

	@DeleteMapping("/post/{postId}")
	@AuthorizeRoles(roles = { RolesConstants.ROLES_BLOGGER, RolesConstants.ROLES_CONTENT_MODERATOR,
			RolesConstants.ROLES_ADMIN })
	public ResponseEntity<ResponseDto<String>> deletePosts(@PathVariable long postId,  HttpServletRequest request) {
		String userId = request.getHeader("USER_ID");
		String roles = request.getHeader("CLAIMS");
		return ResponseEntity.ok(ResponseDto.forSuccess(postService.deletePost(postId, userId, roles)));
	}

	@PutMapping("/post/{postId}")
	@AuthorizeRoles(roles = { RolesConstants.ROLES_BLOGGER, RolesConstants.ROLES_CONTENT_MODERATOR,
			RolesConstants.ROLES_ADMIN })
	public ResponseEntity<ResponseDto<Post>> updatePosts(@RequestBody PostRequest post, @PathVariable long postId) {
		Post updatePost = postService.updatePost(postId, post.getPostName());
		return ResponseEntity.ok(ResponseDto.forSuccess(updatePost));
	}

	///Topics

	@PutMapping("/post/{postId}/topic/{topicId}")
	@AuthorizeRoles(roles = { RolesConstants.ROLES_BLOGGER })
	public ResponseEntity<ResponseDto<PostSummaryResponse>> addPostToTopic(@PathVariable("postId") long postId,
																		@PathVariable("topicId") long topicId) {
		PostSummaryResponse response = postService.addPostToTopic(postId, topicId);
		return ResponseEntity.ok(ResponseDto.forSuccess(response));
	}

	@DeleteMapping("/post/{postId}/topic/{topicId}")
	@AuthorizeRoles(roles = { RolesConstants.ROLES_BLOGGER })
	public ResponseEntity<ResponseDto<String>> removePostFromTopic(@PathVariable("postId") long postId,
																   @PathVariable("topicId") long topicId) {
		String response = postService.removePostFromTopic(postId, topicId);
		return ResponseEntity.ok(ResponseDto.forSuccess(response));
	}

	@DeleteMapping("/posts/topics/{topicId}")
	@AuthorizeRoles(roles = { RolesConstants.ROLES_BLOGGER, RolesConstants.ROLES_ADMIN })
	public ResponseEntity<ResponseDto<String>> deletePostTopics(@PathVariable("topicId") long topicId) {
		String response = postService.deletePostTopicsById(topicId);
		return ResponseEntity.ok(ResponseDto.forSuccess(response));
	}

	@GetMapping("/posts/topics/{topicId}")
	public ResponseEntity<ResponseDto<List<PostSummaryResponse>>> getPostsByTopic(@PathVariable("topicId") long topicId) {
		List<PostSummaryResponse> response = postService.getPostsOfTopic(topicId);
		return ResponseEntity.ok(ResponseDto.forSuccess(response));
	}

}
