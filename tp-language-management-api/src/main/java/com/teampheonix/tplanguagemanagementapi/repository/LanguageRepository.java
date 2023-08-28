package com.teampheonix.tplanguagemanagementapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teampheonix.tplanguagemanagementapi.entity.LanguageContent;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<LanguageContent, Long> {

    LanguageContent findLanguageContentByPostIdAndLanguage(long postId, String Language);

}
