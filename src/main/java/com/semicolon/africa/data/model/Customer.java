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
    private String password;
    private String phoneNumber;
    private String homeAddress;
    private LocalDateTime dateSent;
    private LocalDateTime dateUpdated;
    @OneToMany
    List <Order> orders;
    @OneToMany
    List<Rider> riders;
}
