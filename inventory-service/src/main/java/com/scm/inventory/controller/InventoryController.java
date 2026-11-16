package com.scm.inventory.controller;

import com.scm.inventory.service.InventoryService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

// @RestController - Web endpoints; API Rate Limiting and Throttling - Traffic control (Conceptually placed)
@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    // constructor - Initialization and data passing
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    // HTTP Methods, HTTP Status Codes, RequestParam - REST basics
    @PostMapping("/reserve")
    public ResponseEntity<Boolean> reserveInventory(@RequestParam String productId, @RequestParam int qty) {
        boolean success = inventoryService.reserveStock(productId, qty);
        return ResponseEntity.ok(success);
    }
}