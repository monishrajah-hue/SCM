package com.scm.inventory.repository;

import com.scm.inventory.model.InventoryItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// IoC and DI, @Repository - Dependency management
@Repository
public interface InventoryRepository extends MongoRepository<InventoryItem, String> {
    Optional<InventoryItem> findByProductId(String productId);
}