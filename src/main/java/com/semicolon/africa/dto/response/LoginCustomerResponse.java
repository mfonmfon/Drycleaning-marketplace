package com.semicolon.africa.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginCustomerResponse {
    private String email;
    private String password;
    private String message;
    private boolean isLoggedIn;
}
