package com.my.testprogect.messagingInterfaces;

import com.my.testprogect.model.Message;

public interface MessageListener<T> {
    void handleMessage(Message<T> incomingMessage);
}