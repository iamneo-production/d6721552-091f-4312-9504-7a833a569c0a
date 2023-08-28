package com.teampheonix.tptopicmanagementapi.controller;

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

import com.teampheonix.tptopicmanagementapi.model.ResponseDto;
import com.teampheonix.tptopicmanagementapi.entity.Topic;
import com.teampheonix.tptopicmanagementapi.service.TopicService;

@RestController
@RequestMapping("/api/tp/topic-management/topics")
public class TopicController {

	@Autowired
	private TopicService topicService;

	//create
	//put post
	//delete post
	//get topic by topic id
	//get all topics
	//update topic name
	//delete topic

	@PostMapping("/topic")
	public ResponseEntity<ResponseDto<String>> createTopics(@RequestBody Topic topic) {
		System.out.println("In controller" + topic.getTopicName());
		topicService.createTopic(topic);
		return ResponseEntity.status(HttpStatus.CREATED).body(ResponseDto.forSuccess("topic created Successfully"));

	}

	@GetMapping
	public ResponseEntity<ResponseDto<List<Topic>>> getAlltopics() {
		List<Topic> allTopics = topicService.getAllTopic();
		return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.forSuccess(allTopics));
	}

	@GetMapping("topic12/{topicName}")
	public ResponseEntity<ResponseDto<Topic>> getTopicByTopicName(@PathVariable String topicName) {
		Topic topic = topicService.findByTopicName(topicName);
		if (topic != null) {
			return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.forSuccess(topic));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("topic1/{topicId}")
	public ResponseEntity<ResponseDto<Topic>> getTopicById(@PathVariable String topicId) {
		Topic topic = topicService.findByTopicId(topicId);
		if (topic != null) {
			return ResponseEntity.status(HttpStatus.OK).body(ResponseDto.forSuccess(topic));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/topic3/{topicId}")
	public ResponseEntity<Topic> updateTopic(@PathVariable String topicId, @RequestBody Topic topic) {
		return new ResponseEntity<Topic>(topicService.updateTopic(topic, topicId), HttpStatus.OK);
	}

	@DeleteMapping("/topic4/{topicId}")
	public ResponseEntity<String> deletetopic(@PathVariable String topicId) {
		topicService.deleteTopic(topicId);
		return new ResponseEntity<String>("Topic deleted successfully!!", HttpStatus.OK);

	}

}
