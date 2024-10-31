package com.semicolon.africa.data.model;

import com.semicolon.africa.data.enums.ItemType;
import com.semicolon.africa.data.enums.ServiceType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
@Setter
@Entity
public class OrderPlacement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;
    @Enumerated(EnumType.STRING)
    private ItemType items;
    private BigDecimal price;
    @DateTimeFormat(pattern = "dd/mm/yyyy")
    private LocalDateTime dateOrdered;
    @DateTimeFormat(pattern = "dd/mm/yyyy")
    private LocalDateTime dateUpdated;
}
