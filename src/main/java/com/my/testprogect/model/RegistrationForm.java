package com.my.testprogect.model;

import lombok.*;

@Data
public class RegistrationForm {
    private String login;
    private String password;
    private String email;
    private String fullName;
}
