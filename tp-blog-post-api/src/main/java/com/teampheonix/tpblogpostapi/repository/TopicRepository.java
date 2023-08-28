package com.teampheonix.tpblogpostapi.repository;

import com.teampheonix.tpblogpostapi.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

}
