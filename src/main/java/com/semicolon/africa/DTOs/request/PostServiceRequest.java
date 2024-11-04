package com.semicolon.africa.DTOs.request;

import com.semicolon.africa.data.enums.ServiceType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Blob;
import java.time.LocalDateTime;

@Getter
@Setter
public class PostServiceRequest {
    private Blob imageUrl;
    private ServiceType serviceType;
    private String description;
    private String companyName;
    private String phoneNumber;
    private BigDecimal price;
    private LocalDateTime datePosted;
    private String message;
}
