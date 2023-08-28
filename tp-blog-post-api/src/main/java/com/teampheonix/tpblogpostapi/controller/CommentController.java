package com.teampheonix.tpblogpostapi.controller;

import java.util.List;

import com.teampheonix.tpblogpostapi.aspect.AuthorizeRoles;
import com.teampheonix.tpblogpostapi.aspect.RolesConstants;
import com.teampheonix.tpblogpostapi.entity.Comment;
import com.teampheonix.tpblogpostapi.exception.ApiErrorCodes;
import com.teampheonix.tpblogpostapi.exception.ApiException;
import com.teampheonix.tpblogpostapi.model.ResponseDto;
import com.teampheonix.tpblogpostapi.services.impl.CommentServiceImpl;
import com.teampheonix.tpblogpostapi.services.PostService;
import jakarta.servlet.http.HttpServletRequest;
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

@RestController
@RequestMapping("/api/blog-post/comments")
public class CommentController {

	@Autowired
	private CommentServiceImpl commentService;

	@PostMapping("/{postId}")
	public ResponseEntity<ResponseDto<Comment>> addCommentByPostId(@PathVariable(value = "postId") int postId,
														  @RequestBody Comment comment,
														  HttpServletRequest request) {
		String userId = request.getHeader("USER_ID");
		comment.setUserId(userId);
		return ResponseEntity.ok(ResponseDto.forSuccess(commentService.saveComment(postId, comment)));
	}

	@GetMapping("/{postId}")
	public ResponseEntity<ResponseDto<List<Comment>>> getAllCommentsByPostId(@PathVariable(value = "postId") long postId) {
		return ResponseEntity.ok(ResponseDto.forSuccess(commentService.findByPostId(postId)));
	}

	@PutMapping("/{commentId}")
	public ResponseEntity<ResponseDto<Comment>> updateComment(@RequestBody Comment comment, @PathVariable long commentId) {
		if (commentId !=  comment.getCommentId()) {
			throw new ApiException(ApiErrorCodes.INVALID_REQUEST);
		}
		return ResponseEntity.ok(ResponseDto.forSuccess(commentService.updateComment(commentId, comment)));
	}

	@DeleteMapping("/{postId}/{commentId}")
	@AuthorizeRoles(roles = {RolesConstants.ROLES_ADMIN})
	public ResponseEntity<ResponseDto<String>> deleteComment(@PathVariable("postId") long postId,
															 @PathVariable("commentId") long commentId) {
		return ResponseEntity.ok(ResponseDto.forSuccess(commentService.deleteComment(postId, commentId)));
	}

}
