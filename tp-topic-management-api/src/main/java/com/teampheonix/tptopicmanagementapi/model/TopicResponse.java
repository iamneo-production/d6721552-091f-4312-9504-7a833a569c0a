package com.teampheonix.tptopicmanagementapi.model;

import com.teampheonix.tptopicmanagementapi.entity.Topic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicResponse {

    private Topic topic;
    private List<PostSummaryResponse> posts;

}
