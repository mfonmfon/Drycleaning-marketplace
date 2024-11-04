package com.semicolon.africa.DTOs.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerLoginRequest {
    private String email;
    private String password;
}
