package com.semicolon.africa.DTOs.response;

import com.semicolon.africa.data.model.Rider;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DryCleanerUpdateOrderResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String companyName;
    private String message;
    private Rider rider;
}
