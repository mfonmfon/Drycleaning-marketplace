package com.semicolon.africa.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
public class DryCleaner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String companyName;
    private String phoneNumber;
    private String password;
    private boolean isLoggedIn;
    @OneToMany
    private List<OrderPlacement> orderPlacement;
    @OneToMany
    private List<Customer> customers;
    @OneToMany
    private List<Rider> riders;
}
