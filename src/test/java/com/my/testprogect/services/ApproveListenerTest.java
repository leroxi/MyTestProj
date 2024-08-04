package com.my.testprogect.services;

import com.my.testprogect.model.ApproveResponse;
import com.my.testprogect.model.Message;
import com.my.testprogect.model.UserModel;
import com.my.testprogect.repository.UsersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ApproveListenerTest {

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private ApproveListener approveListener;

    @Test
    public void shouldHandleApprovedMessage() {
        ApproveResponse approveResponse = new ApproveResponse(1L, true, "Registration Approved");
        UserModel user = new UserModel();
        user.setEmail("test@example.com");
        when(usersRepository.findById(1L)).thenReturn(java.util.Optional.of(user));
        Message<ApproveResponse> message = createMessage(approveResponse);
        approveListener.handleMessage(message);
        verify(usersRepository).save(user);
        assertEquals("Approved", user.getStatus());
        verify(emailService).sendEmail(eq("test@example.com"), eq("Registration Approved"), any(String.class));
    }

    @Test
    public void shouldHandleRejectedMessage() {
        ApproveResponse approveResponse = new ApproveResponse(2L, false, "Registration Rejected");
        UserModel user = new UserModel();
        user.setEmail("test@example.com");
        when(usersRepository.findById(2L)).thenReturn(java.util.Optional.of(user));
        Message<ApproveResponse> message = createMessage(approveResponse);
        approveListener.handleMessage(message);
        verify(usersRepository).save(user);
        assertEquals("Rejected", user.getStatus());
        verify(emailService).sendEmail(eq("test@example.com"), eq("Registration Rejected"), any(String.class));
    }

    @Test
    public void shouldThrowExceptionWhenUserNotFound() {
        ApproveResponse approveResponse = new ApproveResponse(3L, true, "Registration Approved");
        when(usersRepository.findById(3L)).thenReturn(java.util.Optional.empty());
        Message<ApproveResponse> message = createMessage(approveResponse);
        assertThrows(RuntimeException.class, () -> approveListener.handleMessage(message));
    }

    private Message<ApproveResponse> createMessage(ApproveResponse approveResponse) {
        Message<ApproveResponse> message = mock(Message.class);
        when(message.getPayload()).thenReturn(approveResponse);
        return message;
    }
}