package com.fral.extreme.Mail;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * In case that we may not have an annotation in MockMailSender but
 * we need to specify it in the Application Context, we can use this
 * Configuration Class.
 */
@Configuration
public class MailConfig {

    /**
     * We require MockMailSender just in DEV environment
     * @return
     */
    @Bean
    @Profile("dev")
    public MailSender mockMailSender() {
        return new MockMailSender();
    }

    /**
     * We require SmtpMailSender in PRODUCTION environment
     * @return
     */
    @Bean
    //@Profile("prod")
    @Profile("!dev")
    public MailSender smtpMailSender() {
        return new SmtpMailSender();
    }
}
