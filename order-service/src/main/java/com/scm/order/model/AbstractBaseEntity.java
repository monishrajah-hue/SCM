package com.scm.order.model;

//Abstract Class - Base classes for shared logic
public abstract class AbstractBaseEntity {
 // Immutability - unchangeable state
 protected final long createdAt = System.currentTimeMillis(); 
 
 public long getCreatedAt() {
     return createdAt;
 }
}
