package com.miaoshaproject.service.impl;

import com.miaoshaproject.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaServiceImpl implements KafkaService {

    private static final String TOPIC = "topic";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void sendMessage(String message) {
        kafkaTemplate.send(TOPIC, "topic message");
    }

    @KafkaListener(topics = TOPIC, groupId = "group-id")
    @Override
    public void listen(String message) {
        System.out.println("Received Message in group - group-id: " + message);
    }
}
