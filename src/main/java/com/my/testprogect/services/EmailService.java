package com.my.testprogect.services;

import com.my.testprogect.messagingInterfaces.SendMailer;
import com.my.testprogect.model.EmailAddress;
import com.my.testprogect.model.EmailContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeoutException;

@Service
public class EmailService {
    @Autowired
    // todo final + reqArgConst
    private SendMailer sendMailer;

    public void sendEmail(String to, String subject, String body) {
        EmailAddress emailAddress = new EmailAddress(to);
        EmailContent emailContent = new EmailContent(subject, body);
        try {
            sendMailer.sendMail(emailAddress, emailContent);
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}