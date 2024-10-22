package com.semicolon.africa.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupCustomerResponse {
    private String fullName;
    private String email;
    private String phoneNumber;
    private String homeAddress;
    private String password;
    private String message;
}
