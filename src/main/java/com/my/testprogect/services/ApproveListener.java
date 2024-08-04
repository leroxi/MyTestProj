package com.my.testprogect.services;

import com.my.testprogect.messagingInterfaces.MessageListener;
import com.my.testprogect.model.ApproveResponse;
import com.my.testprogect.model.Message;
import com.my.testprogect.model.UserModel;
import com.my.testprogect.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApproveListener implements MessageListener<ApproveResponse> {
    private final UsersRepository usersRepository;
    private final EmailService emailService;

    @Override
    public void handleMessage(Message<ApproveResponse> incomingMessage) {
        ApproveResponse approveResponse = incomingMessage.getPayload();
        UserModel user = usersRepository.findById(approveResponse.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        if (approveResponse.isApproved()) {
            user.setStatus("Approved");
            emailService.sendEmail(user.getEmail(), "Registration Approved", "Your registration has been approved.");
        } else {
            user.setStatus("Rejected");
            emailService.sendEmail(user.getEmail(), "Registration Rejected", "Your registration has been rejected.");
        }
        usersRepository.save(user);
    }
}
