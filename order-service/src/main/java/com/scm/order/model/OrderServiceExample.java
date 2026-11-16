package com.scm.order.model;

class OrderServiceExample {
    public void processOrder() {
        // var Keyword (Local Variable Type Inference) - Concise local variables
        var newProduct = new ProductRecord("PROD-99", "Industrial Sensor");
        var newOrder = new OrderRecord("ORD-1024", "PENDING", newProduct);
        
        // Text Blocks - Multi-line strings
        String jsonPayload = """
            {
                "orderId": "%s",
                "status": "%s"
            }
            """.formatted(newOrder.orderId(), newOrder.status());
            
        // String Templates - String interpolation (Java 21 feature)
        String logMessage = String.format("Processing payload for order: %s", newOrder.orderId());
        
        System.out.println(logMessage);
    }
}