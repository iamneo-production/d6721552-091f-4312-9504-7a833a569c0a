package com.teampheonix.tptopicmanagementapi.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teampheonix.tptopicmanagementapi.exception.InvalidRequestException;
import com.teampheonix.tptopicmanagementapi.model.Topic;
import com.teampheonix.tptopicmanagementapi.repository.TopicRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TopicServiceImpl implements TopicService {

	@Autowired
	private TopicRepository topicRepository;

	@Override
	public void createTopic(Topic topic) {
		Topic findByTopicName = topicRepository.findByTopicName(topic.getTopicName());
		if (findByTopicName != null) {
			log.error("topic is already Exist");
			throw new InvalidRequestException("topic is already Exist");
		}
		Topic topicEntity = new Topic();
		String randomTopicId = UUID.randomUUID().toString();
		topicEntity.setTopicId(randomTopicId);
		topicEntity.setTopicName(topic.getTopicName());
		topicEntity.setCreatedBy(topic.getCreatedBy());
		topicEntity.setCreationTimestamp(topic.getCreationTimestamp());
		topicEntity.setLastUpdatedBy(topic.getLastUpdatedBy());
		topicEntity.setLastUpdatedTimestamp(topic.getLastUpdatedTimestamp());
		topicRepository.save(topicEntity);
		log.info("Topic created successfully");
	}

	@Override
	public List<Topic> getAllTopic() {
		// TODO Auto-generated method stub
		return topicRepository.findAll();
	}

	@Override
	public Topic findByTopicName(String topicName) {
		if (topicName.isEmpty()) {
			log.error("Topic is not exist");
			throw new InvalidRequestException("topic is not  Exist");			
		}
		return topicRepository.findByTopicName(topicName);
	}

	@Override
	public Topic findByTopicId(String topicId) {
		Optional<Topic> findByTopicId = topicRepository.findById(topicId);
		System.out.println(findByTopicId);
		if (findByTopicId.isEmpty()) {
			log.error("Topic is not exist");
			throw new InvalidRequestException("topic is not  Exist");			
		}
		return topicRepository.findBytopicId(topicId);
	}

	public Topic updateTopic(Topic topic, String topicId) {
		Optional<Topic> findByTopicId = topicRepository.findById(topicId);
		System.out.println(findByTopicId);
		if (findByTopicId.isEmpty()) {
			log.error("Topic is not exist");
			throw new InvalidRequestException("topic is not  Exist");
			
		}
		topic.setTopicId(topic.getTopicId());
		topic.setTopicName(topic.getTopicName());
		topic.setCreatedBy(topic.getCreatedBy());
		topic.setLastUpdatedBy(topic.getLastUpdatedBy());
		topic.setCreationTimestamp(topic.getCreationTimestamp());
		topic.setLastUpdatedTimestamp(topic.getLastUpdatedTimestamp());
		topicRepository.save(topic);
		log.info("Topic updated successfully.");
		return topic;
	}

	@Override
	public String deleteTopic(String topicId) {
		
		Optional<Topic> findByTopicId = topicRepository.findById(topicId);
		System.out.println(findByTopicId);
		if (findByTopicId.isEmpty()) {
			log.error("Topic is not exist");
			throw new InvalidRequestException("topic is not  Exist");
			
		}
		topicRepository.delete(findByTopicId.get());
		log.info("Topic deleted successfully.");
		return "Deleted successfully";

	}

}
