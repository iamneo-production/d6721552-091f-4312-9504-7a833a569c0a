package com.teampheonix.tpblogpostapi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "topic")
@Data
public class Topic {

    @Id
    public Long topicId;

}
