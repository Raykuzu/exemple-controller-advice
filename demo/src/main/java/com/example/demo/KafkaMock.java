package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class KafkaMock {

    public <T> void sendMessage(final String topic, final T body) {
        System.out.println("Kafka message sent: " + topic +  ", " + body.toString());
    }
}
