package com.scm.order.service;

import java.util.concurrent.Executors;

public class ConcurrencyManager {
    
    // Race Condition - concurrent data corruption; Deadlock - threads waiting indefinitely; synchronized - locking; volatile - memory visibility
    private volatile boolean systemHalted = false;

    public void executeBackgroundTasks() {
        // Threads, Virtual Threads, Multithreading, Thread Pooling, ExecutorService
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            executor.submit(() -> validateInventoryStock());
            executor.submit(() -> notifyWarehouse());
        }
    }

    private synchronized void validateInventoryStock() {
        if (!systemHalted) {
            // Critical section logic safely locked per instance
        }
    }
    
    private void notifyWarehouse() {
        // Background notification logic
    }
}