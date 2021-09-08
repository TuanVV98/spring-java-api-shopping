package com.spring.model.email.context;

import com.spring.model.User;

import org.springframework.web.util.UriComponentsBuilder;

public class UserVerificationEmailContext extends AbstractEmailContext {

    private String token;

    @Override
    public <T> void init(T context) {
        User user = (User) context;

        put("username", user.getUsername());
        setTemplateLocation("emails/email-verification");
        setSubject("Complete your registration");
        setFrom("tuan.vu.v.98.gmail.com");
        setTo(user.getEmail());
    }

    public void setToken(String token) {
        this.token = token;
        put("token", token);
    }

    public void buildVerificationUrl(final String baseURL, final String token) {
        final String url = UriComponentsBuilder.fromHttpUrl(baseURL)
                .path("/api/v1/user/register/verify").queryParam("token", token).toUriString();
        put("verificationURL", url);
    }
}
