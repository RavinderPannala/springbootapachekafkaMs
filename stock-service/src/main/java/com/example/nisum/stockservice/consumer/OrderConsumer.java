package com.example.nisum.stockservice.consumer;

import com.example.nisum.basedomains.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    private Logger logger = LoggerFactory.getLogger(OrderConsumer.class);

    @KafkaListener(topics = "order", groupId = "stock",containerFactory = "kafkaListenerContainerFactory")
    public void consume(OrderEvent orderEvent) {
        logger.info("Order event Received {}" + orderEvent);
    }
}
