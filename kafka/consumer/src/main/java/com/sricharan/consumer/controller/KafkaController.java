package com.sricharan.consumer.controller;

import com.sricharan.consumer.service.KafkaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
@AllArgsConstructor
public class KafkaController {
    private KafkaService kafkaService;

    @GetMapping("/courses")
    public ResponseEntity<String> getCourse() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(kafkaService.getMessage());
    }
}
