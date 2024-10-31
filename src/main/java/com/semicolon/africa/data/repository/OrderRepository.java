package com.semicolon.africa.data.repository;

import com.semicolon.africa.data.model.OrderPlacement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderPlacement, Long> {
}
