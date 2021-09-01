package com.spring.service.verificationToken;

import com.spring.model.VerificationToken;
import com.spring.repository.VerificationTokenRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class VerificationTokenServiceImpl implements VerificationTokenService{

    private static final BytesKeyGenerator DEFAULT_TOKEN_GENERATOR = KeyGenerators.secureRandom(15);
    private static final Charset US_ASCII = Charset.forName("US-ASCII");

    @Value("${jdj.secure.token.validity}")
    private int tokenValidityInSeconds;

    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    public VerificationTokenServiceImpl(VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }

    @Override
    public VerificationToken createVerifiedToken() {
        String tokenValue = new String(Base64.encodeBase64URLSafe(DEFAULT_TOKEN_GENERATOR.generateKey()),US_ASCII);
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(tokenValue);
        verificationToken.setExpiresAt(LocalDateTime.now().plusSeconds(getTokenValidityInSeconds()));

        return verificationToken;
    }

    @Override
    public void saveVerifiedToken(VerificationToken token) {
        this.verificationTokenRepository.save(token);
    }

    @Override
    public Optional<VerificationToken> findByToken(String token) {
        return this.verificationTokenRepository.findByTokenAndVerifiedAtIsNull(token);
    }

    @Override
    public Optional<VerificationToken> checkConfirmToken(String token) {
        return this.verificationTokenRepository.findByTokenAndVerifiedAtIsNull(token);
    }

    @Override
    public Optional<VerificationToken> checkValidToken(String token) {
        return this.verificationTokenRepository.findByToken(token);
    }

    public int getTokenValidityInSeconds() {
        return tokenValidityInSeconds;
    }

}
