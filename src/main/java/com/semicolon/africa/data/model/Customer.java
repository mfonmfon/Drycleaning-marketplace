package com.semicolon.africa.data.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "FirstName is required")
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30")
    private String firstName;
    @NotBlank(message = "LastName is required")
    @Size(min = 2, max = 30, message = "LastName must be between 2 and 30")
    private String lastName;
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "PhoneNumber is required")
    @Size(min = 1, max = 11)
    private String phoneNumber;
    private String password;
    private boolean isLoggedIn;
    @OneToMany
    private List<OrderPlacement> orderPlacement;
    
}
