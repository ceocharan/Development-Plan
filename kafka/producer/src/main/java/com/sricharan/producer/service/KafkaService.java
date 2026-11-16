package com.sricharan.producer.service;

import com.sricharan.producer.entity.Course;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaService {
    private KafkaTemplate<String, Course> kafkaTemplate;
    public String sendMessage(Course course) {
        kafkaTemplate.send("topic","course", course);
        return "Course Message sent to Kafka server";
    }
}
