package com.semicolon.africa.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    private ServiceType serviceType;
    private ItemsType itemsType;
    private String detailedInstructions;
    private LocalDateTime orderedAt;
    private LocalDateTime updatedOrderAt;
}
