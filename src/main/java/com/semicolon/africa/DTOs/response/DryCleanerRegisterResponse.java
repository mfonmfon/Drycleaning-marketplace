package com.semicolon.africa.DTOs.response;

import com.semicolon.africa.data.model.Rider;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DryCleanerRegisterResponse {
    private Long dryCleanerId;
    private String email;
    private String phoneNumber;
    private String password;
    private String message;

}
