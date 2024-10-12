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
    private Long orderId;
    private Long customerId;
    private String fullName;
    private String phoneNumber;
    private String city;
    private String street;
    private String country;
    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;
    @Enumerated(EnumType.STRING)
    private ItemsType itemsType;
    private String detailedInstructions;
    private LocalDateTime updatedDate;
    private String message;
}
