package com.semicolon.africa.data.repository;

import com.semicolon.africa.data.model.OrderPlacement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderPlacement, Long> {
    OrderPlacement findOrderById(Long orderPlacementId);

    Optional<Long> findOrderPlacementById(Long id);

}
