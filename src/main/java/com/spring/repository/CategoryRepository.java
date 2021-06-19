package com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	/**
	 * Method Query creation findBy... in JPArepository
	 * 
	 * @since 07/06/2021
	 * 
	 * @return <code>List<Category></code> Object
	 * {@link} https://viblo.asia/p/java-springboot-spring-data-jpa-1-tong-quan-va-khai-niem-jvElayjDlkw
	 */
	List<Category>   findByDeletedAtIsNull();
	
	List<Category>   findByDeletedAtIsNotNull();
}
