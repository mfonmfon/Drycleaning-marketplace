package com.semicolon.africa.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Rider {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    private Long dryCleanerId;
    private String fullName;
    private String email;
    private String password;
    private String phoneNumber;
    private String homeAddress;
    private LocalDateTime pickedUpAt;
    @OneToMany
    private List<DryCleaner> dryCleaners;
    @OneToMany
    private List<Customer> customers;
}
