package com.spring.repository;

import com.spring.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    @Query(value = "SELECT up FROM UserProfile up "
                    + "WHERE up.user.id =:id")
    Optional<UserProfile> findByUserId(@Param("id") Long id);
}
