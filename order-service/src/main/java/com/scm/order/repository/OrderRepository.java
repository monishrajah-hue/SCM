package com.scm.order.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.scm.order.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}