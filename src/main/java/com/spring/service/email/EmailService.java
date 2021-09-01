package com.spring.service.email;

import com.spring.model.email.context.AbstractEmailContext;

import javax.mail.MessagingException;

public interface EmailService {

    void sendEmail(final AbstractEmailContext email) throws MessagingException;
}
