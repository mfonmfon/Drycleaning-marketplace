package com.semicolon.africa.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
public class SendOrderRequest {
    private String email;
    private String phoneNumber;
    private String homeAddress;
    private LocalDateTime sendAt;
}
