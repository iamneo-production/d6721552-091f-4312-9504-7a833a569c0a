package com.teampheonix.tptopicmanagementapi.entity;

import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Topic {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long topicId;
	@Column(unique = true)
	private String topicName;
	private String createdBy; 
	private String lastUpdatedBy;
    private LocalDateTime creationTimestamp;
    private LocalDateTime lastUpdatedTimestamp;

    @PrePersist
    protected void onCreate() {
        creationTimestamp = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        lastUpdatedTimestamp = LocalDateTime.now();
    }

}
