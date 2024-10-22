package com.semicolon.africa.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupCustomerRequest {
    private String fullName;
    private String email;
    private String phoneNumber;
    private String homeAddress;
    private String password;

}
