package com.teampheonix.tplanguagemanagementapi.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teampheonix.tplanguagemanagementapi.entity.Language;

public interface LanguageRepository extends JpaRepository<Language, Integer>{


}
