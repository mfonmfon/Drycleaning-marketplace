package com.semicolon.africa.DTOs.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerLoginResponse {
    private String email;
    private String password;
    private String message;
}
