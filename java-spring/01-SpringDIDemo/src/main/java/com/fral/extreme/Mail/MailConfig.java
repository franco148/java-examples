package com.fral.extreme.Mail;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * In case that we may not have an annotation in MockMailSender but
 * we need to specify it in the Application Context, we can use this
 * Configuration Class.
 */
@Configuration
public class MailConfig {

    @Bean
    public MailSender mockMailSender() {
        return new MockMailSender();
    }

    @Bean
    public MailSender smtpMailSender() {
        return new SmtpMailSender();
    }
}
