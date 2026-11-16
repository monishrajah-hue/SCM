package com.scm.inventory.service;

import com.scm.inventory.model.InventoryItem;
import com.scm.inventory.repository.InventoryRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

// @Service - Dependency management
@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public boolean reserveStock(String productId, int requestedQuantity) {
        // Optional - Avoid null pointer exceptions
        Optional<InventoryItem> itemOpt = inventoryRepository.findByProductId(productId);
        
        if (itemOpt.isPresent()) {
            InventoryItem item = itemOpt.get();
            if (item.getStockQuantity() >= requestedQuantity) {
                item.setStockQuantity(item.getStockQuantity() - requestedQuantity);
                inventoryRepository.save(item); // MongoDB Transactions - NoSQL principles
                return true;
            }
        }
        return false;
    }
}