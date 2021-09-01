package com.spring.repository;

import com.spring.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByDeletedAtIsNull();

    List<Category> findByDeletedAtIsNotNull();
}
