package com.example.nisum.orderservice.controller;

import com.example.nisum.basedomains.dto.Order;
import com.example.nisum.basedomains.dto.OrderEvent;
import com.example.nisum.orderservice.kafka.OrderProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    OrderProducer orderProducer;

    @PostMapping("/save")
    public String placeOrder(@RequestBody Order order) {
        order.setOrderId(UUID.randomUUID().toString());
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setStatus("PENDING");
        orderEvent.setMessage("order status is pending");
        orderEvent.setOrder(order);
        orderProducer.sendMessage(orderEvent);
        return "Order Placed Successfully";
    }
}
