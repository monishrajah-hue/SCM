package com.scm.order.messaging;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderEventProducer {
    
    private final KafkaTemplate<String, String> kafkaTemplate;

    public OrderEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishOrderEvent(String orderId, String status) {
        String payload = STR."{\"orderId\":\"\{orderId}\", \"status\":\"\{status}\"}";
        // Sends message to the "scm-order-events" Topic distributed across Partitions on the Confluent Brokers
        kafkaTemplate.send("scm-order-events", orderId, payload);
    }
}