package com.my.testprogect.model;

import lombok.Getter;

@Getter
public class EmailContent {
    private String subject;
    private String body;

    public EmailContent(String subject, String body) {
        this.subject = subject;
        this.body = body;
    }
}