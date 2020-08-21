package com.miaoshaproject.message;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.util.ArrayList;
import java.util.List;

public class MessageSubscriber implements MessageListener {

    public static List<String> messages = new ArrayList<>();

    @Override
    public void onMessage(Message message, byte[] bytes) {
        messages.add(message.toString());
        System.out.println("Message received: " + new String(message.getBody()));
    }
}
