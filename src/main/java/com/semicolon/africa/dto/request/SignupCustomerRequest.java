package com.semicolon.africa.dto.request;

import com.semicolon.africa.data.model.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class SignupCustomerRequest {
    private String fullName;
    private String email;
    private Gender gender;
    private String password;

}
