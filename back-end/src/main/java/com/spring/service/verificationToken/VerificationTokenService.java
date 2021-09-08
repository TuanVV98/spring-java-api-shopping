package com.spring.service.verificationToken;

import com.spring.model.VerificationToken;

import java.util.Optional;

public interface VerificationTokenService {

    VerificationToken createVerifiedToken();

    void saveVerifiedToken(VerificationToken token);

    Optional<VerificationToken> findByToken(String token);

    Optional<VerificationToken> checkConfirmToken(String token);

    Optional<VerificationToken> checkValidToken(String token);

}
