package com.semicolon.africa.dto.response;

import com.semicolon.africa.data.model.ItemsType;
import com.semicolon.africa.data.model.ServiceType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class UpdateCustomersOrderResponse {
    private Long customerId;
    private Long orderId;
    private String fullName;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;
    @Enumerated(EnumType.STRING)
    private ItemsType itemsType;
    private String detailedInstructions;
    private LocalDateTime updatedDate;
    private String message;
}
