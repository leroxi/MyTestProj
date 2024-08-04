package com.my.testprogect.services;

import com.my.testprogect.messagingInterfaces.SendMailer;
import com.my.testprogect.model.EmailAddress;
import com.my.testprogect.model.EmailContent;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EmailServiceTest {

    @Mock
    private SendMailer sendMailer;

    @InjectMocks
    private EmailService emailService;

    public EmailServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldSendEmail() throws Exception {
        String to = "test@test.com";
        String subject = "Test Subject";
        String body = "Test Body";
        emailService.sendEmail(to, subject, body);
        ArgumentCaptor<EmailAddress> emailAddressCaptor = ArgumentCaptor.forClass(EmailAddress.class);
        ArgumentCaptor<EmailContent> emailContentCaptor = ArgumentCaptor.forClass(EmailContent.class);
        verify(sendMailer).sendMail(emailAddressCaptor.capture(), emailContentCaptor.capture());

        assertEquals(to, emailAddressCaptor.getValue().getAddress());
        assertEquals(subject, emailContentCaptor.getValue().getSubject());
        assertEquals(body, emailContentCaptor.getValue().getBody());
    }

    @Test
    public void shouldHandleTimeoutException() throws TimeoutException {
        String to = "test@test.com";
        String subject = "Test Subject";
        String body = "Test Body";
            doThrow(new TimeoutException()).when(sendMailer).sendMail(any(EmailAddress.class), any(EmailContent.class));
        assertDoesNotThrow(() -> emailService.sendEmail(to, subject, body));
    }
}