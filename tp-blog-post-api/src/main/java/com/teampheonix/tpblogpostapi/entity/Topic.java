package com.teampheonix.tpblogpostapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "topic")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Topic {

    @Id
    public Long topicId;

}
