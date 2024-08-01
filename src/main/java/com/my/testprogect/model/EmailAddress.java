package com.my.testprogect.model;

import lombok.Getter;

@Getter
public class EmailAddress {
    private String address;

    public EmailAddress(String address) {
        this.address = address;
    }

}