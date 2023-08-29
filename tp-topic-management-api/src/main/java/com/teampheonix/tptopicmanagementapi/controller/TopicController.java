package com.teampheonix.tptopicmanagementapi.controller;

import java.util.List;

import com.teampheonix.tptopicmanagementapi.aspect.AuthorizeRoles;
import com.teampheonix.tptopicmanagementapi.aspect.RolesConstants;
import com.teampheonix.tptopicmanagementapi.model.TopicResponse;
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

import com.teampheonix.tptopicmanagementapi.model.ResponseDto;
import com.teampheonix.tptopicmanagementapi.entity.Topic;
import com.teampheonix.tptopicmanagementapi.service.TopicService;

@RestController
@RequestMapping("/api/tp/topic-management/topics")
public class TopicController {

	@Autowired
	private TopicService topicService;

	@PostMapping("/topic")
	@AuthorizeRoles(roles = { RolesConstants.ROLES_BLOGGER })
	public ResponseEntity<ResponseDto<Topic>> createTopics(@RequestBody Topic topic, HttpServletRequest request) {
		String userId = request.getHeader("USER_ID");
		String roles = request.getHeader("CLAIMS");
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ResponseDto.forSuccess(topicService.createTopic(topic, userId, roles)));
	}

	@PutMapping("/{topicId}")
	@AuthorizeRoles(roles = { RolesConstants.ROLES_BLOGGER, RolesConstants.ROLES_ADMIN,
			RolesConstants.ROLES_CONTENT_MODERATOR })
	public ResponseEntity<ResponseDto<Topic>> updateTopic(@PathVariable long topicId,
											 @RequestBody Topic topic,
											 HttpServletRequest request) {
		String userId = request.getHeader("USER_ID");
		return ResponseEntity.ok(ResponseDto.forSuccess(topicService.updateTopic(topic, topicId, userId)));
	}

	@DeleteMapping("/{topicId}")
	@AuthorizeRoles(roles = { RolesConstants.ROLES_BLOGGER, RolesConstants.ROLES_ADMIN,
			RolesConstants.ROLES_CONTENT_MODERATOR })
	public ResponseEntity<ResponseDto<String>> deleteTopic(@PathVariable long topicId,  HttpServletRequest request) {
		String userId = request.getHeader("USER_ID");
		String roles = request.getHeader("CLAIMS");
		return ResponseEntity.ok(ResponseDto.forSuccess(topicService.deleteTopic(topicId, userId, roles)));
	}

	@PutMapping("/{topicId}/post/{postId}")
	@AuthorizeRoles(roles = { RolesConstants.ROLES_BLOGGER })
	public ResponseEntity<ResponseDto<TopicResponse>> addPostToTopic(@PathVariable("topicId") long topicId,
																	 @PathVariable("postId") long postId,
																	 HttpServletRequest request) {
		String userId = request.getHeader("USER_ID");
		String roles = request.getHeader("CLAIMS");
		return ResponseEntity.ok(ResponseDto.forSuccess(topicService.addPostToTopic(topicId, postId, userId, roles)));
	}

	@DeleteMapping("/{topicId}/post/{postId}")
	@AuthorizeRoles(roles = { RolesConstants.ROLES_BLOGGER })
	public ResponseEntity<ResponseDto<String>> deleteTopic(@PathVariable("topicId") long topicId,
														   @PathVariable("postId") long postId,
														   HttpServletRequest request) {
		String userId = request.getHeader("USER_ID");
		String roles = request.getHeader("CLAIMS");
		return ResponseEntity.ok(ResponseDto.forSuccess(topicService.removePostFromTopic(topicId, postId, userId, roles)));
	}

	@GetMapping("/all")
	public ResponseEntity<ResponseDto<List<TopicResponse>>> getAllTopics(HttpServletRequest request) {
		String userId = request.getHeader("USER_ID");
		String roles = request.getHeader("CLAIMS");
		List<TopicResponse> allTopics = topicService.getAllTopic(userId, roles);
		return ResponseEntity.ok(ResponseDto.forSuccess(allTopics));
	}

	@GetMapping("/{topicId}")
	public ResponseEntity<ResponseDto<TopicResponse>> getTopicById(@PathVariable long topicId,
																   HttpServletRequest request) {
		String userId = request.getHeader("USER_ID");
		String roles = request.getHeader("CLAIMS");
		return ResponseEntity.ok(ResponseDto.forSuccess(topicService.findByTopicId(topicId, userId, roles)));
	}

}
