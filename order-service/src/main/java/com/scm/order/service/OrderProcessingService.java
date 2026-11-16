package com.scm.order.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Isolation;

@Service
public class OrderProcessingService {
    // @Transactional - atomic operations; Database Deadlocks - circular lock wait
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void processOrder(Long orderId) {
        // Business logic here
    }
}