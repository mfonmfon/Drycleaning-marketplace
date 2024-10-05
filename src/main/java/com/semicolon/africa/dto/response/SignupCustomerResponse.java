package com.semicolon.africa.dto.response;

import com.semicolon.africa.data.model.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class SignupCustomerResponse {
    private String fullName;
    private String email;
    private Gender gender;
    private String password;
    private String message;
}
