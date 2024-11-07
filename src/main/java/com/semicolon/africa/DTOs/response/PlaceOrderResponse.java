package com.semicolon.africa.DTOs.response;

import com.semicolon.africa.data.enums.ItemType;
import com.semicolon.africa.data.enums.ServiceType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class PlaceOrderResponse {
    private Long orderId;
    private Long customerId;
    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;
    @Enumerated(EnumType.STRING)
    private ItemType items;
    private BigDecimal price;
    private Integer quantity;
    @DateTimeFormat(pattern = "dd/mm/yyyy")
    private LocalDateTime dateOrdered;
    @DateTimeFormat(pattern = "dd/mm/yyyy")
    private String message;
}

