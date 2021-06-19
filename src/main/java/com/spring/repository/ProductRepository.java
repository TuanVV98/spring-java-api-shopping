package com.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.entity.Category;
import com.spring.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	/**
	 * Method Query creation findBy... in JPArepository
	 * 
	 * @since 07/06/2021
	 * 
	 * @return <code>List<Category></code> Object
	 * {@link} https://viblo.asia/p/java-springboot-spring-data-jpa-1-tong-quan-va-khai-niem-jvElayjDlkw
	 */
	List<Product> findByCategoryId(Long categoryID);
	
	Optional<Product> findByModel(String model);

	List<Product>   findByDeletedAtIsNull();
	
	List<Product>   findByDeletedAtIsNotNull();
}
