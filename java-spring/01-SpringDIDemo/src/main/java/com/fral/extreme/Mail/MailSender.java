package com.fral.extreme.Mail;

public interface MailSender {

    void send(String to, String subject, String body);
}
