package com.semicolon.africa.DTOs.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DryCleanerLoginRequest {
    private Long dryCleanerId;
    private String email;
    private String password;
}
