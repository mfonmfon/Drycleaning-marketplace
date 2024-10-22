package com.semicolon.africa.dto.request;

import com.semicolon.africa.data.model.ItemsType;
import com.semicolon.africa.data.model.Order;
import com.semicolon.africa.data.model.ServiceType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class CustomerSendsAnOrderRequest {
//    private Long customerId;
    private String phoneNumber;
    private String homeAddress;
    private String email;
    private Long orderId;
    private Long dryCleanerId;

//    @Enumerated(EnumType.STRING)
//    private ServiceType serviceType;
//    @Enumerated(EnumType.STRING)
//    private ItemsType itemsType;
//    private String detailedInstructions;
    private LocalDateTime dateSent;

}
