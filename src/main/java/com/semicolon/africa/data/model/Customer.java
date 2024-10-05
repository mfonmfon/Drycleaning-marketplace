package com.semicolon.africa.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long dryCleanerId;
    private String fullName;
    private String email;
    private Gender gender;
    private String password;
    private String phoneNumber;
    private String homeAddress;
    private String city;
    private String street;
    private String country;
    private LocalDateTime dateSent;
    private LocalDateTime dateUpdated;
}
