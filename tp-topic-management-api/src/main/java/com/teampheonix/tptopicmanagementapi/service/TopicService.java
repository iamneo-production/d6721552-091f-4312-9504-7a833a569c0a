package com.teampheonix.tptopicmanagementapi.service;

import java.util.List;
import com.teampheonix.tptopicmanagementapi.entity.Topic;

public interface TopicService {

	void createTopic(Topic topic);
	Topic findByTopicName(String topicName);
	List<Topic> getAllTopic();
	Topic findByTopicId(String topicId);
	Topic updateTopic(Topic topic, String topicId);
	String deleteTopic(String topicId);

}
