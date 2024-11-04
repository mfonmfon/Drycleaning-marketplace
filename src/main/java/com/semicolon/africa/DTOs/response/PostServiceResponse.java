package com.semicolon.africa.DTOs.response;

import com.semicolon.africa.data.enums.ServiceType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Blob;
import java.time.LocalDateTime;

@Setter
@Getter
public class PostServiceResponse {
    private Long id;
    private Blob imageUrl;
    private ServiceType serviceType;
    private String description;
    private String companyName;
    private String phoneNumber;
    private BigDecimal price;
    private LocalDateTime datePosted;
    private String message;
}
