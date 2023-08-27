package com.teampheonix.tptopicmanagementapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teampheonix.tptopicmanagementapi.model.Topic;





public interface TopicRepository extends JpaRepository<Topic, String> {

	Topic findBytopicId(String topicId);
	Topic findByTopicName(String topicName);
	void deleteByTopicId(String topicId);

	

}
