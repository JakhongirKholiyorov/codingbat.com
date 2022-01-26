package com.codingbat.codingbat.repository;

import com.codingbat.codingbat.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Integer> {

    boolean existsByName(String name);

}
