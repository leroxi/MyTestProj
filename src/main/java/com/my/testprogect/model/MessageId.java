package com.my.testprogect.model;

import lombok.Getter;

@Getter
public class MessageId {
    private String id;

    public MessageId(String id) {
        this.id = id;
    }

}
