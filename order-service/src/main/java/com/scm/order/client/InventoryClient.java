package com.scm.order.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class InventoryClient {

    private final RestTemplate restTemplate = new RestTemplate();

    // Microservices patterns
    @CircuitBreaker(name = "inventoryService", fallbackMethod = "fallbackInventoryCheck")
    @Retry(name = "inventoryService")
    public boolean reserveStock(String productId, int quantity) {
        String inventoryApiUrl = "http://api-gateway/api/inventory/reserve?productId=" + productId + "&qty=" + quantity;
        return restTemplate.postForObject(inventoryApiUrl, null, Boolean.class);
    }

    public boolean fallbackInventoryCheck(String productId, int quantity, Throwable throwable) {
        System.out.println("Inventory Service unavailable. Triggering fallback for: " + productId);
        return false; // Fallback response to safely degrade functionality
    }
}