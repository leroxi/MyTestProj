package com.my.testprogect.services;

import com.my.testprogect.messagingInterfaces.MessagingService;
import com.my.testprogect.model.Message;
import com.my.testprogect.model.RegistrationForm;
import com.my.testprogect.model.UserModel;
import com.my.testprogect.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
// todo чо за пустая строка
public class RegistrationService {
    // todo final
    private UsersRepository usersRepository;
    // todo final
    private MessagingService messagingService;

    @Autowired
    // todo requiredArgsConstructor
    public RegistrationService(UsersRepository usersRepository, MessagingService messagingService) {
        this.usersRepository = usersRepository;
        this.messagingService = messagingService;
    }

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