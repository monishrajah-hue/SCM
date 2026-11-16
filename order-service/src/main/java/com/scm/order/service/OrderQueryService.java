package com.scm.order.service;

import com.scm.order.model.OrderRecord;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class OrderQueryService {

    // Performance tuning
    @Cacheable(value = "orders", key = "#orderId")
    public OrderRecord getOrderDetails(String orderId) {
        // Fetches from DB only if missing from cache
        return null; 
    }

    @CachePut(value = "orders", key = "#order.orderId()")
    public OrderRecord updateOrderDetails(OrderRecord order) {
        // Updates DB and replaces the cache entry
        return order;
    }

    @CacheEvict(value = "orders", key = "#orderId")
    public void removeOrder(String orderId) {
        // Deletes from DB and evicts from cache
    }
}