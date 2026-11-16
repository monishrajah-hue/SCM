package com.scm.inventory.messaging;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
public class InventoryEventConsumer {

    // Kafka Offsets - reading position; Message Acknowledgment - delivery confirmation; DLQ - failed message routing
    @KafkaListener(topics = "scm-order-events", groupId = "inventory-consumer-group")
    public void consumeOrderEvent(String payload, Acknowledgment acknowledgment) {
        try {
            System.out.println("Received event: " + payload);
            // Process inventory deduction logic here
            
            acknowledgment.acknowledge(); // Manual commit of offset to confirm successful processing
        } catch (Exception e) {
            System.err.println("Failed to process message, routing to Dead Letter Queue (DLQ): " + payload);
            // Logic to forward payload to a "scm-order-events-dlq" topic for manual review
        }
    }
}