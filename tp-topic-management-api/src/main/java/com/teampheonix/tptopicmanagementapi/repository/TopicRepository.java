package com.teampheonix.tptopicmanagementapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teampheonix.tptopicmanagementapi.entity.Topic;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

    Topic findTopicByTopicName(String topicName);

}
