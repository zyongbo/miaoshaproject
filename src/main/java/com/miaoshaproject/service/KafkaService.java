package com.miaoshaproject.service;

public interface KafkaService {
    public void sendMessage(String message);
    public void listen(String message);
}
