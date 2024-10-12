package com.semicolon.africa.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.security.Provider;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long OrderId;
    private String fullName;
    private String email;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String password;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;
    @Enumerated(EnumType.STRING)
    private ItemsType itemsType;
    private String detailedInstructions;
    private String city;
    private String street;
    private String country;
    private LocalDateTime dateSent;
    private LocalDateTime dateUpdated;
}
