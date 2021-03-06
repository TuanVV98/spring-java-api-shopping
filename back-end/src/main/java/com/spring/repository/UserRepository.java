package com.spring.repository;

import com.spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT u FROM User u "
                + "WHERE u.email= :email ")
    Optional<User> checkIfEmailExists(@Param("email") String email);

    @Query(value = "SELECT u FROM User u "
            + "WHERE u.email= :email "
            + "AND u.status LIKE 'IS_ACTIVE'")
    Optional<User> checkIfEmailExistsAndStatus(@Param("email") String email);

    @Query(value = "SELECT u FROM User u "
            + "WHERE u.username = :username ")
    Optional<User> checkIfUsernameExists(@Param("username") String username);

    @Query(value = "SELECT u FROM User u "
            + "WHERE u.username = :username "
            + "AND u.status LIKE 'IS_ACTIVE'")
    Optional<User> findByUsername(@Param("username") String username);

    List<User> findByDeletedAtIsNull();
}
