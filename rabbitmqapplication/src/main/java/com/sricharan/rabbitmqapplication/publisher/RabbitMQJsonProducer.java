package com.sricharan.rabbitmqapplication.publisher;

import com.sricharan.rabbitmqapplication.dto.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RabbitMQJsonProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.json.routing.key}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    public void sendJsonMessage(User user) {
        log.info("Json Message Sent -> {}", user.toString());
        rabbitTemplate.convertAndSend(exchange, routingKey, user);
    }
}
