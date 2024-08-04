package com.my.testprogect.services;

import com.my.testprogect.messagingInterfaces.MessagingService;
import com.my.testprogect.model.Message;
import com.my.testprogect.model.RegistrationForm;
import com.my.testprogect.model.UserModel;
import com.my.testprogect.repository.UsersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RegistrationServiceTest {


@Mock
private UsersRepository usersRepository;

@Mock
private MessagingService messagingService;

@InjectMocks
private RegistrationService registrationService;

@Test
public void shouldRegisterUserAndSendMessage() {
    RegistrationForm registrationForm = new RegistrationForm();
    registrationForm.setLogin("test");
    registrationForm.setPassword("test");
    registrationForm.setEmail("test@test.com");
    registrationForm.setFullName("testName");
    UserModel user = new UserModel();
    user.setLogin(registrationForm.getLogin());
    user.setPassword(registrationForm.getPassword());
    user.setEmail(registrationForm.getEmail());
    user.setFullName(registrationForm.getFullName());
    registrationService.registerUser(registrationForm);
    verify(usersRepository).save(eq(user));
    ArgumentCaptor<Message<RegistrationForm>> messageCaptor = ArgumentCaptor.forClass(Message.class);
    verify(messagingService).send(messageCaptor.capture());
    Message<RegistrationForm> capturedMessage = messageCaptor.getValue();
    assertNotNull(capturedMessage);
    assertEquals(registrationForm.getLogin(), capturedMessage.getPayload().getLogin());
    assertEquals(registrationForm.getPassword(), capturedMessage.getPayload().getPassword());
    assertEquals(registrationForm.getEmail(), capturedMessage.getPayload().getEmail());
    assertEquals(registrationForm.getFullName(), capturedMessage.getPayload().getFullName());
}
}