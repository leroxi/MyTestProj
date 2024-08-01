package com.my.testprogect.messagingInterfaces;

import com.my.testprogect.model.EmailAddress;
import com.my.testprogect.model.EmailContent;

import java.util.concurrent.TimeoutException;

public interface SendMailer {
    void sendMail(EmailAddress toAddress, EmailContent messageBody) throws TimeoutException;
}
