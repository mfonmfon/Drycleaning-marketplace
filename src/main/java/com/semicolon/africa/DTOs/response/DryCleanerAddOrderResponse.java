package com.semicolon.africa.DTOs.response;

import com.semicolon.africa.data.model.Rider;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DryCleanerAddOrderResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Rider rider;
    private String message;
}
