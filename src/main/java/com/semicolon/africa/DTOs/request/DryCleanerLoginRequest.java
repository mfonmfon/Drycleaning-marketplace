package com.semicolon.africa.DTOs.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DryCleanerLoginRequest {
    private String email;
    private String password;
}
