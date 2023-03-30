package com.example.nisum.orderservice.kafka;

import com.example.nisum.basedomains.dto.OrderEvent;
import com.example.nisum.orderservice.producer.KafkaConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    @Autowired
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;

    Logger logger = LoggerFactory.getLogger(OrderProducer.class);

    public void sendMessage(OrderEvent orderEvent) {
        logger.info(String.format("Order event => %s", orderEvent.toString()));

        Message<OrderEvent> message = MessageBuilder.
                withPayload(orderEvent).
                setHeader(KafkaHeaders.TOPIC, KafkaConfig.TOPIC_NAME)
                .build();
        kafkaTemplate.send(message);
    }
}
