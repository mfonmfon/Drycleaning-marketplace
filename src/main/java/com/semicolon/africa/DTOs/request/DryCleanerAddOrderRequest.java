package com.semicolon.africa.DTOs.request;

import com.semicolon.africa.data.model.Rider;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DryCleanerAddOrderRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String companyName;
    private Rider rider;
}
