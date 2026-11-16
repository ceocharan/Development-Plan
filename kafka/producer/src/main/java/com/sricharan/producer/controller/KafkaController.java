package com.sricharan.producer.controller;

import com.sricharan.producer.entity.Course;
import com.sricharan.producer.service.KafkaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
@AllArgsConstructor
public class KafkaController {
    private KafkaService kafkaService;

    @PostMapping("/courses")
    public ResponseEntity<String> addCourse(@RequestBody Course course) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(kafkaService.sendMessage(course));
    }
}
