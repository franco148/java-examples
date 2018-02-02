package com.fral.extreme.Controllers;

import com.fral.extreme.Mail.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {

    @Autowired
    private MailSender mailSender;

    @RequestMapping("/mail")
    public String mail() {

        mailSender.send("franck_ral@hotmail.com", "A test mail", "Body of the test mail");

        return "Sending mail";
    }
}
