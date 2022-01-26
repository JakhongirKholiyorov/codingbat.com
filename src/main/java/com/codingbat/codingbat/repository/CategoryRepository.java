package com.codingbat.codingbat.repository;

import com.codingbat.codingbat.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
        boolean existsByNameAndLanguageId(String name, Integer language_id);
}
