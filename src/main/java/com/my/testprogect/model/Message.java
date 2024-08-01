package com.my.testprogect.model;

import lombok.Getter;

@Getter
public class Message<T> {
    private T payload;

    public Message(T payload) {
        this.payload = payload;
    }

}