package com.my.testprogect.services;

import com.my.testprogect.messagingInterfaces.MessagingService;
import com.my.testprogect.model.Message;
import com.my.testprogect.model.RegistrationForm;
import com.my.testprogect.model.UserModel;
import com.my.testprogect.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final UsersRepository usersRepository;
    private final MessagingService messagingService;

    public void registerUser(RegistrationForm registrationForm) {
        UserModel user = new UserModel();
        user.setLogin(registrationForm.getLogin());
        user.setPassword(registrationForm.getPassword());
        user.setEmail(registrationForm.getEmail());
        user.setFullName(registrationForm.getFullName());
        usersRepository.save(user);
        Message<RegistrationForm> message = new Message<>(registrationForm);
        messagingService.send(message);
    }
}
