package com.fral.extreme.Controllers;

import com.fral.extreme.Mail.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {

    //@Autowired
    private MailSender mailSender;

    @Value("${app.name}")
    private String appName;


    /**
     * This also work as previous example. It is also going to take mail sender from
     * application problems.
     *
     * We may have problems when there are more than a bean from MailSender, there are many ways
     * for fixing this.
     *
     * 1. Add the same name of the class that we want to use, in camel case.
     *
     * @Autowired
     * public void setMailSender(MailSender smptMailSender) {}
     *
     * 2. According the name that is set in component annotation.
     * @Component("smtp")
     * public class SmtpMailSender {}
     *
     * 3. Using @Primary annotation. So this is going to take for Dependency Injection
     *
     * 4. Having a @Qualifier annotation
     * @Autowired
     * public void setMailSender(@Qualifier("smtpMail") MailSender mailSender) {}
     *
     *
     * There are another annotations that help us to work with Dependency Injection like @Inject and @Resource
     * */
    @Autowired
    public void setMailSender(MailSender mockMailSender) {
        this.mailSender = mockMailSender;
    }

    @RequestMapping("/mail")
    public String mail() {

        mailSender.send("franck_ral@hotmail.com", "A test mail", "Body of the test mail");

        return "Sending mail " + appName;
    }
}
