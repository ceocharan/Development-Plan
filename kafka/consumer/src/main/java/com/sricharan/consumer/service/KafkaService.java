package com.sricharan.consumer.service;

import com.sricharan.consumer.entity.Course;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Getter
@Service
@Slf4j
public class KafkaService {
    private String message;
    @KafkaListener(topics="topic", groupId="consumer-group")
    public void sendMessage(Course course) {
        message = course + " got the data from kafka";
        log.warn(message);
    }
}
