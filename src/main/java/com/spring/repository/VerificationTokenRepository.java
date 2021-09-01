package com.spring.repository;


import com.spring.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    Optional<VerificationToken> findByTokenAndVerifiedAtIsNull(String token);

    Optional<VerificationToken> findByToken(String token);
}
