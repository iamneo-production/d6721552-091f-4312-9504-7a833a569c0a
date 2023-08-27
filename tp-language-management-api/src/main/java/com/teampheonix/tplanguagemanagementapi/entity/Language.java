package com.teampheonix.tplanguagemanagementapi.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name="Language_Management")
public class Language {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int language_content_id;
	String user_id;
	String content;
	String language;
	String created_by;
//	Timestamp created_date;
	LocalDateTime created_date;
	String last_updated_by;
//	Timestamp last_updated_date;
	@Transient
	LocalDateTime last_updated_date;
	
	public Language() {
		super();
	}
	
	public Language(int language_content_id, String user_id, String content, String language, String created_by,
			LocalDateTime created_date, String last_updated_by, LocalDateTime last_updated_date) {
		super();
		this.language_content_id = language_content_id;
		this.user_id = user_id;
		this.content = content;
		this.language = language;
		this.created_by = created_by;
		this.created_date = created_date;
		this.last_updated_by = last_updated_by;
		this.last_updated_date = last_updated_date;
	}
	
	public int getLanguage_content_id() {
		return language_content_id;
	}
	public void setLanguage_content_id(int language_content_id) {
		this.language_content_id = language_content_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public LocalDateTime getCreated_date() {
		return created_date;
	}
	public void setCreated_date(LocalDateTime created_date) {
		this.created_date = created_date;
	}
	public String getLast_updated_by() {
		return last_updated_by;
	}
	public void setLast_updated_by(String last_updated_by) {
		this.last_updated_by = last_updated_by;
	}
	public LocalDateTime getLast_updated_date() {
		return last_updated_date;
	}
	public void setLast_updated_date(LocalDateTime last_updated_date) {
		this.last_updated_date = last_updated_date;
	}
	
	@Override
	public String toString() {
		return "Language [language_content_id=" + language_content_id + ", user_id=" + user_id + ", content=" + content
				+ ", language=" + language + ", created_by=" + created_by + ", created_date=" + created_date
				+ ", last_updated_by=" + last_updated_by + ", last_updated_date=" + last_updated_date + "]";
	}
	
	
	

}
