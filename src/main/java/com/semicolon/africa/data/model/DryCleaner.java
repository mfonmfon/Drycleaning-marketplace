package com.semicolon.africa.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class DryCleaner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    private String fullName;
    private String email;
    private String password;
    private String phoneNumber;
    private String homeAddress;
    private LocalDateTime finishedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;

    @OneToMany
   private List<Customer> customers;

    @OneToMany
    private List<Rider> riders;
}
