package com.scm.order.controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    // HTTP Methods - GET, POST, PUT, DELETE; Idempotency - safe retries
    @PostMapping("/{orderId}")
    public ResponseEntity<String> createOrder(@PathVariable String orderId, @RequestParam boolean expedite) {
        return new ResponseEntity<>("Order " + orderId + " created", HttpStatus.CREATED);
    }
}