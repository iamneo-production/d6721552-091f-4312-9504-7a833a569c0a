package com.teampheonix.tptopicmanagementapi.service;

import java.util.List;
import com.teampheonix.tptopicmanagementapi.model.Topic;


public interface TopicService {

	void createTopic(Topic topic);
	Topic findByTopicName(String topicName);
	public List<Topic> getAllTopic();
	Topic findByTopicId(String topicId);
	Topic updateTopic(Topic topic, String topicId);
	String deleteTopic(String topicId);
}
