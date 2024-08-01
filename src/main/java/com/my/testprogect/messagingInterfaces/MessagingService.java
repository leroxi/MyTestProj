package com.my.testprogect.messagingInterfaces;

import com.my.testprogect.model.Message;
import com.my.testprogect.model.MessageId;

import java.util.concurrent.TimeoutException;

public interface MessagingService {

    <T> MessageId send(Message<T> msg);

    <T> Message<T> receive(MessageId messageId, Class<T> messageType) throws TimeoutException;
}
