package com.teampheonix.tptopicmanagementapi.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.teampheonix.tptopicmanagementapi.client.BlogPostClientApi;
import com.teampheonix.tptopicmanagementapi.exception.ApiErrorCodes;
import com.teampheonix.tptopicmanagementapi.exception.ApiException;
import com.teampheonix.tptopicmanagementapi.model.PostSummaryResponse;
import com.teampheonix.tptopicmanagementapi.model.TopicResponse;
import com.teampheonix.tptopicmanagementapi.service.TopicService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teampheonix.tptopicmanagementapi.entity.Topic;
import com.teampheonix.tptopicmanagementapi.repository.TopicRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TopicServiceImpl implements TopicService {

	private static final String API_KEY = "dHAtYmxvZy1wb3N0LWFwaQ==";

	@Autowired
	private TopicRepository topicRepository;

	@Autowired
	private BlogPostClientApi blogPostClientApi;

	@Override
	public Topic createTopic(Topic topic, String userId, String roles) {
		Topic existingTopic = topicRepository.findTopicByTopicName(topic.getTopicName());
		if (existingTopic != null) {
			log.error("topic is already Exist");
			throw new ApiException(ApiErrorCodes.INVALID_REQUEST);
		}
		Topic topicEntity = new Topic();
		topicEntity.setTopicName(topic.getTopicName());
		topicEntity.setUserId(userId);
		topicEntity.setCreatedBy(userId);
		return topicRepository.save(topicEntity);
	}

	@Override
	public Topic updateTopic(Topic topicRequest, long topicId, String userId) {
		Topic topic = topicRepository.findById(topicId)
				.orElseThrow(() -> new ApiException(ApiErrorCodes.TOPIC_NOT_FOUND));
		topic.setTopicName(topicRequest.getTopicName());
		topic.setLastUpdatedBy(userId);
		return topicRepository.save(topic);
	}

	@Override
	@Transactional
	public String deleteTopic(long topicId, String userId, String roles) {
		Topic topic = topicRepository.findById(topicId)
				.orElseThrow(() -> new ApiException(ApiErrorCodes.TOPIC_NOT_FOUND));
		String response = blogPostClientApi.deleteTopicPosts(topicId, API_KEY, userId, roles).getData();
		log.info(response);
		topicRepository.delete(topic);
		return "Deleted successfully";
	}

	@Override
	public TopicResponse addPostToTopic(long topicId, long postId, String userId, String roles) {
		Topic topic = topicRepository.findById(topicId)
				.orElseThrow(() -> new ApiException(ApiErrorCodes.TOPIC_NOT_FOUND));
		PostSummaryResponse post = blogPostClientApi.addPostToTopic(postId, topicId, API_KEY, userId, roles).getData();
		return new TopicResponse(topic, List.of(post));
	}

	@Override
	public String removePostFromTopic(long topicId, long postId, String userId, String roles) {
		topicRepository.findById(topicId)
				.orElseThrow(() -> new ApiException(ApiErrorCodes.TOPIC_NOT_FOUND));
		return blogPostClientApi.removePostFromTopic(postId, topicId, API_KEY, userId, roles).getData();
	}

	@Override
	public List<TopicResponse> getAllTopic(String userId, String roles) {
		List<TopicResponse> responseList = new ArrayList<>();
		List<Topic> topics = topicRepository.findAll();
		topics.forEach(t -> {
			List<PostSummaryResponse> responses =
					blogPostClientApi.getPostsByTopicId(t.getTopicId(), API_KEY, userId, roles).getData();
			responseList.add(new TopicResponse(t, responses));
		});
		return responseList;
	}

	@Override
	public TopicResponse findByTopicId(long topicId, String userId, String roles) {
		Topic topic = topicRepository.findById(topicId)
				.orElseThrow(() -> new ApiException(ApiErrorCodes.TOPIC_NOT_FOUND));
		List<PostSummaryResponse> responses =
				blogPostClientApi.getPostsByTopicId(topicId, API_KEY, userId, roles).getData();
		return new TopicResponse(topic, responses);
	}

}
