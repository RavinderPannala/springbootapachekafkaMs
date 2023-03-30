package com.example.nisum.emailservice.consumer;

import com.example.nisum.basedomains.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EmailConsumer {

    private Logger logger = LoggerFactory.getLogger(EmailConsumer.class);

    @KafkaListener(topics = "order", groupId = "email",containerFactory = "kafkaListenerContainerFactory")
    public void consume(OrderEvent orderEvent) {
        logger.info("Order event Received and please sent mail {}" + orderEvent);
    }
}
