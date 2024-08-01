package com.my.testprogect.controller;

import com.my.testprogect.model.RegistrationForm;
import com.my.testprogect.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;
    @PostMapping
    public ResponseEntity<String> register(@RequestBody RegistrationForm registrationForm) {
     registrationService.registerUser(registrationForm);
     return ResponseEntity.ok("Registration Successful");
    }
}
