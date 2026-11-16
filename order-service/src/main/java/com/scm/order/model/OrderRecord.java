package com.scm.order.model;

public record OrderRecord(String orderId, String status, ProductRecord product) implements SCMEntity {
    
    // Deep, and Shallow Copy - Cloning objects fully vs referencing
    public OrderRecord shallowCopy() {
        // Returns a new record referencing the exact same nested ProductRecord object
        return new OrderRecord(this.orderId, this.status, this.product);
    }
    
    public OrderRecord deepCopy() {
        // Creates a completely new instance of the nested ProductRecord object
        return new OrderRecord(
            this.orderId, 
            this.status, 
            new ProductRecord(this.product.productId(), this.product.name())
        );
    }
}