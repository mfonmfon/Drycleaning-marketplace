package com.semicolon.africa.DTOs.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRegistrationRequest {
    private String firstName;
    @NotBlank(message = "LastName is required")
    @Size(min = 2, max = 30, message = "LastName must be between 2 and 30")
    private String lastName;
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "PhoneNumber is required")
    @Size(min = 1, max = 11)
    private String phoneNumber;
    private String password;
}
