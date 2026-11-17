package com.sricharan.rabbitmqapplication.consumer;

import com.sricharan.rabbitmqapplication.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitMQJsonConsumer {
    @RabbitListener(queues = "${rabbitmq.json.queue.name}")
    public void consumeJsonMessage(User user) {
        log.info("Received Json Message -> {}", user.toString());
    }
}
