package com.semicolon.africa.DTOs.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DryCleanerRegisterRequest {
    private String email;
    private String firstName;
    private String phoneNumber;
    private String password;

}
