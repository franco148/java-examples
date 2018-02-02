package com.fral.extreme.Mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * In case that we may not have an annotation in MockMailSender but
 * we need to specify it in the Application Context, we can use this
 * Configuration Class.
 */
@Configuration
public class MailConfig {

    /**
     * Or instead of doing this, since the methods already have @Bean annotation
     * we can send javaMailSender as parameter, so it is going to be injected automatically.
     * like so:
     *
     * @Bean
     * @ConditionalOnProperty("spring.mail.host")
     * public MailSender smtpMailSender(JavaMailSender javaMailSender) {}
     */
//    @Autowired
//    private JavaMailSender javaMailSender;

    /**
     * When the class is using @Configuration annotation, then demoBean is called only once even
     * if you call specifically this method from some of the other ones.
     *
     * So If class uses @Component, then you will see in the log that this methods is called more
     * than once.
     * @return
     */
    @Bean
    public DemoBean demoBean() {
        return new DemoBean();
    }

    /**
     * We require MockMailSender just in DEV environment when PROFILE.ACTIVE in application.properties file.
     * @return
     */
    @Bean
    //@Profile("dev")
    @ConditionalOnProperty(name = "spring.mail.host", havingValue = "foo", matchIfMissing = true)
    public MailSender mockMailSender() {
        return new MockMailSender();
    }

    /**
     * We require SmtpMailSender in PRODUCTION environment
     * @return
     */
    @Bean
    //@Profile("prod")
    //@Profile("!dev")
    @ConditionalOnProperty("spring.mail.host")
    public MailSender smtpMailSender(JavaMailSender javaMailSender) {
        return new SmtpMailSender(javaMailSender);
    }
}
