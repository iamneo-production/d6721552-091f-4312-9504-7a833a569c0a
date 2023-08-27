package com.project.blogs.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.blogs.model.Topic;

public interface TopicRepo extends JpaRepository<Topic, Integer> {

}
