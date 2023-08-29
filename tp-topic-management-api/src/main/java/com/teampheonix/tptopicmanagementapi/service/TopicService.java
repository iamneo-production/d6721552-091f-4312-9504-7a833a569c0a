package com.teampheonix.tptopicmanagementapi.service;

import java.util.List;
import com.teampheonix.tptopicmanagementapi.entity.Topic;
import com.teampheonix.tptopicmanagementapi.model.TopicResponse;

public interface TopicService {

	Topic createTopic(Topic topic, String userId, String roles);
	Topic updateTopic(Topic topic, long topicId, String userId);
	String deleteTopic(long topicId, String userId, String roles);
	TopicResponse addPostToTopic(long topicId, long postId, String userId, String roles);
	String removePostFromTopic(long topicId, long postId, String userId, String roles);
	List<TopicResponse> getAllTopic(String userId, String roles);
	TopicResponse findByTopicId(long topicId, String userId, String roles);

}
