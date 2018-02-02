package com.fral.extreme.Mail;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class MockMailSender implements MailSender {

    private static Log log = LogFactory.getLog(MockMailSender.class);

    @Override
    public void send(String to, String subject, String body) {
        log.info("Sending MOCK mail to " + to);
        log.info("with subject " + subject);
        log.info("and body " + body);
    }
}
