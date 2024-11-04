package com.semicolon.africa.data.model;

import com.semicolon.africa.data.enums.ServiceType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.math.BigDecimal;
import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class DryCleaner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String email;
//    private Image image;
    private String companyName;
    private String phoneNumber;
    private String password;
    private ServiceType serviceType;
    private String description;
    private BigDecimal price;
    private boolean isLoggedIn;
    private LocalDateTime datePosted;
    @OneToMany
    private List<OrderPlacement> orderPlacement;
    @OneToMany
    private List<Customer> customers;
//    @OneToMany
//    private List<Rider> riders;
}
