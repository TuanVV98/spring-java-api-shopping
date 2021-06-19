package com.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	
	/**
	 * Method tìm kiếm User thông qua email
	 * 
	 * @since 11/06/2021
	 * 
	 * @param email
	 * @return Optional<User>
	 */
	Optional<User> findByEmailAndDeletedAtIsNull(String email);
	
	Optional<User> findById(Long i);
	
	List<User> findByDeletedAtIsNull();
	
	List<User> findByDeletedAtIsNotNull();
}
