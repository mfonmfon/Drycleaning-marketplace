package com.semicolon.africa.data.repository;

import com.semicolon.africa.data.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
