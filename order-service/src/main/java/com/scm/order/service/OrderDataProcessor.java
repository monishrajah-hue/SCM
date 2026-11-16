package com.scm.order.service;

import com.scm.order.model.OrderRecord;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class OrderDataProcessor {

    // Stream vs Collection - functional processing vs storage; Lambda - anonymous functions; Functional Interface - single abstract method
    public List<String> getExpeditedProductNames(List<OrderRecord> orders) {
        return orders.stream()
                .filter(order -> "EXPEDITED".equals(order.status()))
                .map(OrderRecord::product)
                .flatMap(product -> Stream.of(product.name().toUpperCase()))
                .toList();
    }

    public void processEvent(Object event) {
        // Pattern Matching for instanceof and switch
        switch (event) {
            case OrderRecord o when o.status().equals("PENDING") -> System.out.println("Processing: " + o.orderId());
            case Optional<?> opt when opt.isEmpty() -> System.out.println("Event payload missing");
            default -> System.out.println("Unknown event format");
        }
    }
}